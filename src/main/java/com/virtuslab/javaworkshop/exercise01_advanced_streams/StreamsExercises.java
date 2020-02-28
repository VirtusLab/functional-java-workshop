package com.virtuslab.javaworkshop.exercise01_advanced_streams;

import com.virtuslab.javaworkshop.application.Deliveries;
import com.virtuslab.javaworkshop.application.Orders;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.time.Period;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class StreamsExercises {

    private Orders orders;

    private Deliveries deliveries;

    public Map<String, BigDecimal> calculatePricePerBasketId() {
        // TODO return map containing basket price per basket id 
        // return orders.getBaskets() ...

        return null;
    }
    
    public Map<String, Double> averageDeliveryOfProducts() {
        // TODO using Collectors return average quantity in deliveries of ordered products
        // return orders.getBaskets() ...
        
        return null;
    }

    public Map<String, Double> averageDeliveryOfProductsInGivenTime(Period period) {
        // TODO using Collectors return average quantity in deliveries of ordered products in a given period of time
        // return null;
        return null;
    }
    
    public Baskets splitBaskets(double splitingAmount) {
        // TODO using Collectors split baskets into Baskets where cheapIds contains ids of baskets below limit, and expensiveIds contains baskets above limit
        // orders.getBaskets() ...
        return new Baskets(null, null);
    }

    @Value
    public static class Baskets {
        private String cheapIds;
        private String expensiveIds;
    }

    public List<Integer> calculateDaysToDeliveryWithMoreThan10Products(String productId) {
        /*
          TODO calculate days to delivery with more than 10 products
          E.g if today is 17-02, and deliveries are: 13pcs. at 20-02, 4pcs. at 21-02, 12pcs. at 23-02
          it should return: [3, 6]
        */
        // return deliveries.get(productId) ...
        return null;
    }

}
