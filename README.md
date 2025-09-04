# Event-Driven Order Processing System

This project implements a simplified event-driven order processing backend in Java, simulating an e-commerce workflow.

## Features
- Processes the following events:
  - **OrderCreated**
  - **PaymentReceived**
  - **ShippingScheduled**
  - **OrderCancelled**
- Maintains order status transitions (PENDING → PAID / PARTIALLY_PAID → SHIPPED or CANCELLED).
- Persists event history for each order.
- Implements the **Observer Pattern** with:
  - `LoggerObserver` → logs all events & status changes.
  - `AlertObserver` → prints alerts for critical changes.

## Input
- Events are read from a JSON file (one JSON object per line).
- Example: `src/main/resources/events.json`
```json
{"eventId":"e1","timestamp":"2025-07-29T10:00:00Z","eventType":"OrderCreated","orderId":"ORD001","customerId":"CUST001","items":[{"itemId":"P001","qty":2}],"totalAmount":100.0}
{"eventId":"e2","timestamp":"2025-07-29T10:05:00Z","eventType":"PaymentReceived","orderId":"ORD001","amountPaid":100.0}
{"eventId":"e3","timestamp":"2025-07-29T10:10:00Z","eventType":"ShippingScheduled","orderId":"ORD001","shippingDate":"2025-07-30"}



# Compile
mvn clean install

# Run the program
mvn exec:java -Dexec.mainClass="com.example.orderprocessing.Main"
