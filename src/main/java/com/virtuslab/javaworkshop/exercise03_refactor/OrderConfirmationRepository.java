package com.virtuslab.javaworkshop.exercise03_refactor;

import java.util.List;

public interface OrderConfirmationRepository {
    List<OrderConfirmation> getOrders(int year, int size, int offset);
}
