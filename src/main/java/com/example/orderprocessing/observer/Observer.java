package com.example.orderprocessing.observer;

import com.example.orderprocessing.event.Event;
import com.example.orderprocessing.model.Order;
import com.example.orderprocessing.model.OrderStatus;

public interface Observer {
    void onEventProcessed(Event event, Order order);
    void onStatusChanged(String orderId, OrderStatus oldStatus, OrderStatus newStatus);
}
