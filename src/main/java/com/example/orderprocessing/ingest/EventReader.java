package com.example.orderprocessing.ingest;

import com.example.orderprocessing.event.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class EventReader {
    private final ObjectMapper mapper = new ObjectMapper()
            .findAndRegisterModules();

    public List<Event> readEvents(Path path) throws IOException {
        List<Event> events = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank())
                    continue;
                ObjectNode node = (ObjectNode) mapper.readTree(line);
                // String type = node.get("eventType").asText();
                String type = node.get("eventType").asText();
                EventType eventType;
                try {
                    eventType = EventType.valueOf(type);
                } catch (IllegalArgumentException iae) {
                    System.out.printf("[WARN] Skipping unknown eventType: %s%n", type);
                    continue; // skip bad line
                }

                Event e = switch (EventType.valueOf(type)) {
                    case OrderCreated -> mapper.treeToValue(node, OrderCreatedEvent.class);
                    case PaymentReceived -> mapper.treeToValue(node, PaymentReceivedEvent.class);
                    case ShippingScheduled -> mapper.treeToValue(node, ShippingScheduledEvent.class);
                    case OrderCancelled -> mapper.treeToValue(node, OrderCancelledEvent.class);
                };
                events.add(e);
            }
        }
        return events;
    }
}
