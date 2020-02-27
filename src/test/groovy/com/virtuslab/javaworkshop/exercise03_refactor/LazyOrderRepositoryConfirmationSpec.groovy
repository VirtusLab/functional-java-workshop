package com.virtuslab.javaworkshop.exercise03_refactor

import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime
import java.util.stream.Collectors

class LazyOrderRepositoryConfirmationSpec extends Specification {

    OrderConfirmationRepository orderRepository = Mock()
    LazyOrderConfirmationRepository lazyOrderRepository = new LazyOrderConfirmationRepository(orderRepository)

    @Unroll
    def "should fetch data lazy batchSize: #batchSize, size: #size, executions: #repositoryExecutions"() {
        given:
        def anyYear = 2019

        when:
        def result = lazyOrderRepository.getOrders(anyYear, size, batchSize).collect(Collectors.toList())

        then:
        result.size() == size
        repositoryExecutions * orderRepository.getOrders(_, batchSize, _) >> { orderRepositoryResponse(batchSize) }

        where:
        batchSize | size | repositoryExecutions
        1         | 3    | 3
        3         | 9    | 3
        3         | 8    | 3
        10        | 1000 | 100

    }

    def orderRepositoryResponse(int size) {
        (1..size).each {
            OrderConfirmation.of(UUID.randomUUID().toString(), LocalDateTime.now(), 100.00)
        }
    }

}
