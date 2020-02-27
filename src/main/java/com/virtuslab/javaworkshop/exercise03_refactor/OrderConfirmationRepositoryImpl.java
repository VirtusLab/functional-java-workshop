package com.virtuslab.javaworkshop.exercise03_refactor;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderConfirmationRepositoryImpl implements OrderConfirmationRepository {

    @Override
    public List<OrderConfirmation> getOrders(int year, int size, int offset) {
        return generateOrderConfirmation(year, size);
    }

    private static List<OrderConfirmation> generateOrderConfirmation(int year, int size) {
        long from = LocalDateTime.of(year, Month.JANUARY, 1, 0, 0).toEpochSecond(ZoneOffset.UTC);
        long to = LocalDateTime.of(year, Month.DECEMBER, 31, 23, 59).toEpochSecond(ZoneOffset.UTC);
        return new Random().longs(from, to)
                .limit(size)
                .boxed()
                .sorted()
                .map(second -> LocalDateTime.ofEpochSecond(second, 0, ZoneOffset.UTC))
                .map(localDateTime -> OrderConfirmation.of(UUID.randomUUID().toString(), localDateTime, generateAmount()))
                .collect(Collectors.toList());
    }

    private static double generateAmount() {
        Random random = new Random();
        var amountInt = random.nextInt(100000) + 1;
        return (double) amountInt / 100.00;
    }

    public static void main(String[] args) {
        new OrderConfirmationRepositoryImpl().getOrders(2019, 20, 15)
                .forEach(System.out::println);
    }

}
