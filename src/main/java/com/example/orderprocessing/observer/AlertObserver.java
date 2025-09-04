package com.example.orderprocessing.observer;

import com.example.orderprocessing.event.Event;
import com.example.orderprocessing.model.Order;
import com.example.orderprocessing.model.OrderStatus;

public class AlertObserver implements Observer {
    @Override
    public void onEventProcessed(Event event, Order order) {
        // Alerts could be routed to email/SMS in real life
        if (event.getEventType().name().contains("Cancelled")) {
            System.out.printf("[ALERT] Order %s has a critical event: %s%n", order.getOrderId(), event.getEventType());
        }
    }
    @Override
    public void onStatusChanged(String orderId, OrderStatus oldStatus, OrderStatus newStatus) {
        System.out.printf("[ALERT] Sending alert for Order %s: Status changed to %s%n", orderId, newStatus);
    }
}
