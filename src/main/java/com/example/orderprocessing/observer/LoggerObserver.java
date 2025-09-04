package com.example.orderprocessing.observer;

import com.example.orderprocessing.event.Event;
import com.example.orderprocessing.model.Order;
import com.example.orderprocessing.model.OrderStatus;

public class LoggerObserver implements Observer {
    @Override
    public void onEventProcessed(Event event, Order order) {
        System.out.printf("[Logger] Processed %s for order %s%n", event.getEventType(), order.getOrderId());
    }
    @Override
    public void onStatusChanged(String orderId, OrderStatus oldStatus, OrderStatus newStatus) {
        System.out.printf("[Logger] Order %s status: %s -> %s%n", orderId, oldStatus, newStatus);
    }
}
