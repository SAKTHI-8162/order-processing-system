package com.example.orderprocessing.event;

import java.time.LocalDate;

public class ShippingScheduledEvent extends Event {
    private String orderId;
    private LocalDate shippingDate;

    public ShippingScheduledEvent() {
        setEventType(EventType.ShippingScheduled);
    }
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public LocalDate getShippingDate() { return shippingDate; }
    public void setShippingDate(LocalDate shippingDate) { this.shippingDate = shippingDate; }
}
