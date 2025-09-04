package com.example.orderprocessing.model;

import com.example.orderprocessing.event.Event;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private String customerId;
    private List<Item> items = new ArrayList<>();
    private BigDecimal totalAmount = BigDecimal.ZERO;
    private OrderStatus status = OrderStatus.PENDING;
    private List<Event> eventHistory = new ArrayList<>();
    private BigDecimal amountPaid = BigDecimal.ZERO;

    public Order() {}

    public Order(String orderId, String customerId, List<Item> items, BigDecimal totalAmount) {
        this.orderId = orderId;
        this.customerId = customerId;
        if (items != null) this.items = items;
        if (totalAmount != null) this.totalAmount = totalAmount;
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public List<Event> getEventHistory() { return eventHistory; }
    public void setEventHistory(List<Event> eventHistory) { this.eventHistory = eventHistory; }
    public BigDecimal getAmountPaid() { return amountPaid; }
    public void setAmountPaid(BigDecimal amountPaid) { this.amountPaid = amountPaid; }
    public void addPayment(BigDecimal amount) {
        if (amount != null) this.amountPaid = this.amountPaid.add(amount);
    }
}
