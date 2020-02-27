package com.virtuslab.javaworkshop.exercise03_refactor

import com.virtuslab.javaworkshop.common.TestingClock
import spock.lang.Specification

import java.time.Clock
import java.time.Duration
import java.time.Month


class OrderServiceSummarySpec extends Specification {

    Clock clock = new TestingClock()
    private OrderService orderService = new OrderService({ selection -> Availability.availableIn(Duration.ofDays(0)) }, clock, new TestOrderRepositoryImpl())

    def shouldCalculateTotalNumberForEveryMonth() {
        when:
        def ordersPerMonth = orderService.totalAmountPerMonth(10000, 2019)

        then:
        ordersPerMonth.get(Month.JANUARY) > BigDecimal.ZERO
        ordersPerMonth.get(Month.FEBRUARY) > BigDecimal.ZERO
        ordersPerMonth.get(Month.MARCH) > BigDecimal.ZERO
        ordersPerMonth.get(Month.APRIL) > BigDecimal.ZERO
        ordersPerMonth.get(Month.MAY) > BigDecimal.ZERO
        ordersPerMonth.get(Month.JUNE) > BigDecimal.ZERO
        ordersPerMonth.get(Month.JULY) > BigDecimal.ZERO
        ordersPerMonth.get(Month.AUGUST) > BigDecimal.ZERO
        ordersPerMonth.get(Month.SEPTEMBER) > BigDecimal.ZERO
        ordersPerMonth.get(Month.OCTOBER) > BigDecimal.ZERO
        ordersPerMonth.get(Month.NOVEMBER) > BigDecimal.ZERO
        ordersPerMonth.get(Month.DECEMBER) > BigDecimal.ZERO
    }

}
