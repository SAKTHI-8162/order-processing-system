package com.example.orderprocessing.event;

import com.example.orderprocessing.model.Item;
import java.math.BigDecimal;
import java.util.List;

public class OrderCreatedEvent extends Event {
    private String orderId;
    private String customerId;
    private List<Item> items;
    private BigDecimal totalAmount;

    public OrderCreatedEvent() {
        setEventType(EventType.OrderCreated);
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
}
