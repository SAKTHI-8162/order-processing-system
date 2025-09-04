package com.example.orderprocessing.observer;

import com.example.orderprocessing.event.Event;
import com.example.orderprocessing.model.Order;
import com.example.orderprocessing.model.OrderStatus;
import java.util.ArrayList;
import java.util.List;

public class EventPublisher {
    private final List<Observer> observers = new ArrayList<>();

    public void register(Observer observer) { observers.add(observer); }
    public void unregister(Observer observer) { observers.remove(observer); }

    public void notifyEvent(Event event, Order order) {
        for (Observer o : observers) o.onEventProcessed(event, order);
    }
    public void notifyStatus(String orderId, OrderStatus oldStatus, OrderStatus newStatus) {
        for (Observer o : observers) o.onStatusChanged(orderId, oldStatus, newStatus);
    }
}
