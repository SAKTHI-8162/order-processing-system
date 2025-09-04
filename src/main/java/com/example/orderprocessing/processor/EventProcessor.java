package com.example.orderprocessing.processor;

import com.example.orderprocessing.event.*;
import com.example.orderprocessing.model.*;
import com.example.orderprocessing.observer.EventPublisher;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventProcessor {
    private final Map<String, Order> orders = new HashMap<>();
    private final EventPublisher publisher;

    public EventProcessor(EventPublisher publisher) {
        this.publisher = publisher;
    }

    public Map<String, Order> getOrders() { return orders; }

    public void process(List<Event> events) {
        for (Event e : events) process(e);
    }

    public void process(Event event) {
        switch (event.getEventType()) {
            case OrderCreated -> handle((OrderCreatedEvent) event);
            case PaymentReceived -> handle((PaymentReceivedEvent) event);
            case ShippingScheduled -> handle((ShippingScheduledEvent) event);
            case OrderCancelled -> handle((OrderCancelledEvent) event);
            default -> System.out.printf("[WARN] Unsupported event type: %s%n", event.getEventType());
        }
    }

    private void changeStatus(Order order, OrderStatus newStatus) {
        OrderStatus old = order.getStatus();
        if (old != newStatus) {
            order.setStatus(newStatus);
            publisher.notifyStatus(order.getOrderId(), old, newStatus);
        }
    }

    private void handle(OrderCreatedEvent e) {
        Order order = new Order(e.getOrderId(), e.getCustomerId(), e.getItems(), e.getTotalAmount());
        orders.put(order.getOrderId(), order);
        changeStatus(order, OrderStatus.PENDING);
        order.getEventHistory().add(e);
        publisher.notifyEvent(e, order);
    }

    private void handle(PaymentReceivedEvent e) {
        Order order = orders.get(e.getOrderId());
        if (order == null) {
            System.out.printf("[WARN] Payment for unknown order %s%n", e.getOrderId());
            return;
        }
        order.addPayment(e.getAmountPaid());
        BigDecimal due = order.getTotalAmount();
        int cmp = order.getAmountPaid().compareTo(due);
        if (cmp >= 0) changeStatus(order, OrderStatus.PAID);
        else changeStatus(order, OrderStatus.PARTIALLY_PAID);
        order.getEventHistory().add(e);
        publisher.notifyEvent(e, order);
    }

    private void handle(ShippingScheduledEvent e) {
        Order order = orders.get(e.getOrderId());
        if (order == null) {
            System.out.printf("[WARN] Shipping for unknown order %s%n", e.getOrderId());
            return;
        }
        changeStatus(order, OrderStatus.SHIPPED);
        order.getEventHistory().add(e);
        publisher.notifyEvent(e, order);
    }

    private void handle(OrderCancelledEvent e) {
        Order order = orders.computeIfAbsent(e.getOrderId(), id -> new Order());
        order.setOrderId(e.getOrderId());
        changeStatus(order, OrderStatus.CANCELLED);
        order.getEventHistory().add(e);
        publisher.notifyEvent(e, order);
    }
}
