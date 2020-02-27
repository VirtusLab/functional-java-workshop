package com.virtuslab.javaworkshop.exercise01_advanced_streams

import com.virtuslab.javaworkshop.application.Basket
import com.virtuslab.javaworkshop.application.Deliveries
import com.virtuslab.javaworkshop.application.Delivery
import com.virtuslab.javaworkshop.application.Orders
import com.virtuslab.javaworkshop.application.Product
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate
import java.time.Period

import static com.virtuslab.javaworkshop.application.Basket.Selection.selection

class StreamsExercisesSpec extends Specification {

    StreamsExercises streamsExercises
    def static basketId1 = "basketId1"
    def static basketId2 = "basketId2"

    void setup() {
        Basket.Selection selection1 = selection(Product.PLAYSTATION_5.id, 1500.0, 1)
        Basket.Selection selection2 = selection(Product.PLAYSTATION_5.id, 1500.0, 3)
        Basket.Selection selection3 = selection(Product.NINTENDO_SWITCH.id, 2000.0, 2)

        Basket basket = new Basket(basketId1, Set.of(selection1, selection2, selection3))
        Basket basket2 = new Basket(basketId2, Set.of(selection1, selection3))

        Orders orders = Mock(Orders)
        orders.getBaskets() >> [basket, basket2]

        Deliveries deliveries = Mock(Deliveries)
        deliveries.get(Product.PLAYSTATION_5.id) >> [delivery(0, 10), delivery(1, 20), delivery(4, 50)]
        deliveries.get(Product.NINTENDO_SWITCH.id) >> [delivery(0, 1), delivery(1, 5), delivery(5, 20)]

        streamsExercises = new StreamsExercises(orders, deliveries)
    }

    def "should calculate price per basket"() {
        when:
        def pricePerBasket = streamsExercises.calculatePricePerBasketId()
        then:
        pricePerBasket == [basketId1: BigDecimal.valueOf(10000.00), basketId2: BigDecimal.valueOf(5500.00)]
    }
    
    def "should return proper amount of days"() {
        when:
        def daysToCome = streamsExercises.calculateDaysToDeliveryWithMoreThan10Products(Product.PLAYSTATION_5.getId())
        then:
        daysToCome == [1, 4]
    }

    def "should return proper average delivery of products"() {
        when:
        def result = streamsExercises.averageDeliveryOfProducts()

        then:
        result[Product.PLAYSTATION_5.id].round(2) == 26.67d
        result[Product.NINTENDO_SWITCH.id].round(2) == 8.67d
    }

    @Unroll
    def "should return proper average delivery of products in a #period"() {
        when:
        def result = streamsExercises.averageDeliveryOfProductsInGivenTime(period)

        then:
        result[Product.PLAYSTATION_5.id].round(2) == ps5average
        result[Product.NINTENDO_SWITCH.id].round(2) == switchAverage

        where:
        period           || ps5average | switchAverage
        Period.ofDays(6) || 26.67d     | 8.67d
        Period.ofDays(5) || 26.67d     | 8.67d
        Period.ofDays(4) || 26.67d     | 3.0d
        Period.ofDays(2) || 15.0d      | 3.0d
        Period.ofDays(1) || 15.0d      | 3.0d
        Period.ofDays(0) || 10.0d      | 1.0d
    }

    @Unroll
    def "should split baskets according to price #splittingPrice"() {
        when:
        def result = streamsExercises.splitBaskets(splittingPrice)

        then:
        result.cheapIds == below
        result.expensiveIds == upper

        where:
        splittingPrice | below                   | upper
        10000.00       | "$basketId1,$basketId2" | ""
        6000.00        | "$basketId2"            | "$basketId1"
        4000.00        | ""                      | "$basketId1,$basketId2"
    }

    def delivery(int plusDays, quantity) {
        return new Delivery(LocalDate.now().plusDays(plusDays), quantity)
    }
}
