package com.virtuslab.javaworkshop.exercise03_refactor

import com.virtuslab.javaworkshop.application.Basket
import com.virtuslab.javaworkshop.application.Product
import com.virtuslab.javaworkshop.common.TestingClock
import spock.lang.Specification

import java.time.Clock
import java.time.Duration

import static com.virtuslab.javaworkshop.application.Basket.Selection.selection

class OrderServiceSpec extends Specification {

    private String userId = "anyUserId"
    Clock clock = new TestingClock()
    OrderService orderService = new OrderService({ selection -> Availability.availableIn(Duration.ofDays(2)) }, clock, new TestOrderRepositoryImpl())

    def "should return order id when order will be processed"() {
        given:
        Basket.Selection selection = selection(Product.PLAYSTATION_5.id, 1450.0, 1)
        Basket basket = new Basket(UUID.randomUUID().toString(), Set.of(selection))

        when:
        OrderConfirmation orderConfirmation = orderService.reserveOrder(userId, basket, [] as Set)

        then:
        orderConfirmation.orderId != null
    }
    
    def "long response availability checker"() {
        given:
        OrderService orderServiceWithLongResponse = new OrderService(new LongResponseAvailabilityChecker(), clock, new TestOrderRepositoryImpl())
        Basket.Selection selection1 = selection(Product.PLAYSTATION_5.id, 1450.0, 1)
        Basket.Selection selection2 = selection(Product.PLAYSTATION_5.id, 1450.0, 1)
        Basket.Selection selection3 = selection(Product.PLAYSTATION_5.id, 1450.0, 1)
        Basket basket = new Basket(UUID.randomUUID().toString(), Set.of(selection1, selection2, selection3))

        when:
        def orderConfirmation = orderServiceWithLongResponse.reserveOrder(userId, basket, [] as Set)

        then:
        orderConfirmation.orderId != null
    }
    
    def "should pay for order if reservation time didn't exceed 30 days"() {
        given:
        Basket.Selection selection1 = selection(Product.PLAYSTATION_5.id, 1450.0, 1)
        Basket basket = new Basket(UUID.randomUUID().toString(), Set.of(selection1))
        
        when:
        def orderConfirmation = orderService.reserveOrder(userId, basket, [] as Set)
        clock.plus(Duration.ofMinutes(5))
        PaymentConfirmation paymentConfirmation = orderService.payForOrder(userId, orderConfirmation, 100.0)
        
        then:
        paymentConfirmation.paymentId != null
    }
    
    def "should reject order if reservation time passed 30 days"() {
        given:
        Basket.Selection selection1 = selection(Product.PLAYSTATION_5.id, 1450.0, 1)
        Basket.Selection selection2 = selection(Product.PLAYSTATION_5.id, 1450.0, 1)
        Basket.Selection selection3 = selection(Product.PLAYSTATION_5.id, 1450.0, 1)
        Basket basket = new Basket(UUID.randomUUID().toString(), Set.of(selection1, selection2, selection3))

        when: "order is reverved"
        def orderConfirmation = orderService.reserveOrder(userId, basket, [] as Set)
        
        and: "30 days +1 passes"
        clock.plus(Duration.ofDays(31))
        
        and: "order is payed"
        orderService.payForOrder(userId, orderConfirmation, 100.0)
        
        then:
        def exception = thrown(RuntimeException)
        exception.message == "Reservation time exceeded!"
    }
    
//    def "should take order to realization if product will be available shorter that 2 days"

}
