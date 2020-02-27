package com.virtuslab.javaworkshop.exercise03_refactor

import com.virtuslab.javaworkshop.application.Basket
import com.virtuslab.javaworkshop.application.Product
import com.virtuslab.javaworkshop.common.TestingClock
import spock.lang.PendingFeature
import spock.lang.Specification

import java.time.Clock
import java.time.Duration

import static com.virtuslab.javaworkshop.application.Basket.Selection.selection

class OrdersServiceAvailabilitySpec extends Specification {

    private String userId = "anyUserId"
    Clock clock = new TestingClock()
    
    def "should reject order if products are not available"() {
        given:
        Basket.Selection selection = selection(Product.PLAYSTATION_5.id, 1450.0, 1)
        Basket basket = new Basket(UUID.randomUUID().toString(), Set.of(selection))
        OrderService orderServiceWithNoAvailability = new OrderService({ s -> Availability.notAvailable()}, clock, new TestOrderRepositoryImpl())

        when:
        orderServiceWithNoAvailability.reserveOrder(userId, basket, [] as Set)

        then:
        def exception = thrown(RuntimeException)
        exception.message == "Cannot reserve products"
    }
    
    def "should reject order if products are not available shorter than in 3 days"() {
        given:
        Basket.Selection selection = selection(Product.PLAYSTATION_5.id, 1450.0, 1)
        Basket basket = new Basket(UUID.randomUUID().toString(), Set.of(selection))
        OrderService orderService = new OrderService({ s -> Availability.availableIn(Duration.ofDays(4))}, clock, new TestOrderRepositoryImpl())
        
        when:
        orderService.reserveOrder(userId, basket, [] as Set)
        
        then:
        def exception = thrown(RuntimeException)
        exception.message == "Orders are not available shorter that in 3 days"
    }
    
    @PendingFeature
    def "should allow ordering if at least k products are available"() {
        expect:
        1 == 0
    }
    
}
