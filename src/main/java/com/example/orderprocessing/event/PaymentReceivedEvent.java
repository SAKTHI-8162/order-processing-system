package com.example.orderprocessing.event;

import java.math.BigDecimal;

public class PaymentReceivedEvent extends Event {
    private String orderId;
    private BigDecimal amountPaid;

    public PaymentReceivedEvent() {
        setEventType(EventType.PaymentReceived);
    }
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public BigDecimal getAmountPaid() { return amountPaid; }
    public void setAmountPaid(BigDecimal amountPaid) { this.amountPaid = amountPaid; }
}
