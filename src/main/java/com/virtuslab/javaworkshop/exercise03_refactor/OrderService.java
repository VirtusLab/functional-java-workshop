package com.virtuslab.javaworkshop.exercise03_refactor;

import com.virtuslab.javaworkshop.application.Basket;
import com.virtuslab.javaworkshop.application.ProductDiscount;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;


/*
TODO: Refactor imperative code to more functional one.
 
1. Prepare lazy version of OrderConfirmationRepositoryImpl which will return stream instead of list (Test provided)
2. Replace lists with streams
3. Use validation framework from previous exercise if applicable, 
4. Use Vavr constructs (vavr is already included in project dependencies),
5. You can use Vavr future to parallelize availability check
6. Introduce discount applying - only best discount can be applied when calculating price
 */

@RequiredArgsConstructor
public class OrderService {

    private final AvailabilityChecker availabilityChecker;
    private final Clock clock;
    private final OrderRepository orderRepository;
    private OrderConfirmationRepository orderConfirmationRepository = new OrderConfirmationRepositoryImpl();

    public OrderConfirmation reserveOrder(String userId, Basket basket, Set<ProductDiscount> discounts) {
        
        boolean areAllAvailable = true;
        for (Basket.Selection selection : basket.getSelections()) {
            Availability availability = availabilityChecker.checkAvailability(selection);
            if (availability.isAvailable() && availability.getAvailableIn().compareTo(Duration.ofDays(3)) > 0) {
                throw new RuntimeException("Orders are not available shorter that in 3 days");
            } else if (!availability.isAvailable()) {
                areAllAvailable = false;
                break;
            }
        }
        if(!areAllAvailable) {
            throw new RuntimeException("Cannot reserve products");
        }
        var confirmation = OrderConfirmation.of(UUID.randomUUID().toString(), LocalDateTime.now(clock), basket.calculatePrice());

        orderRepository.save(confirmation, basket);

        return confirmation;
    }

    public PaymentConfirmation payForOrder(String userId, OrderConfirmation orderConfirmation, Double paymentAmount) {
        Basket basket = orderRepository.get(orderConfirmation);

        if (basket == null) {
            throw new RuntimeException("Order not found");
        }

        if (Duration.between(orderConfirmation.getReservationDate(), LocalDateTime.now(clock)).compareTo(Duration.ofDays(30)) > 0) {
            throw new RuntimeException("Reservation time exceeded!");
        }

        return PaymentConfirmation.of(UUID.randomUUID().toString());
    }

    // Assumption: ordersToProcess is divided by 100
    public Map<Month, BigDecimal> totalAmountPerMonth(int ordersToProcess, int year) {
        Map<Month, BigDecimal> totalAmountPerMonth = new HashMap<>();

        int limit = 100;
        int batches = ordersToProcess / limit;

        for (int i = 0; i < batches; i++) {
            List<OrderConfirmation> orderConfirmations = orderConfirmationRepository.getOrders(year, limit, i * limit);
            for (OrderConfirmation orderConfirmation : orderConfirmations) {
                if(orderConfirmation.getReservationDate().getYear() == year) {
                    totalAmountPerMonth.merge(orderConfirmation.getReservationDate().getMonth(),
                            BigDecimal.valueOf(orderConfirmation.getTotalAmount()),
                            BigDecimal::add);
                }
            }
        }

        return totalAmountPerMonth;
    }

    interface AvailabilityChecker {
        Availability checkAvailability(Basket.Selection selection);
    }
    
}
