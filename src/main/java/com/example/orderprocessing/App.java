package com.example.orderprocessing;

import com.example.orderprocessing.event.Event;
import com.example.orderprocessing.ingest.EventReader;
import com.example.orderprocessing.observer.AlertObserver;
import com.example.orderprocessing.observer.EventPublisher;
import com.example.orderprocessing.observer.LoggerObserver;
import com.example.orderprocessing.processor.EventProcessor;

import java.nio.file.Path;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        String file = args.length > 0 ? args[0] : "src/main/resources/sample-events.jsonl";
        EventReader reader = new EventReader();
        List<Event> events = reader.readEvents(Path.of(file));

        EventPublisher publisher = new EventPublisher();
        publisher.register(new LoggerObserver());
        publisher.register(new AlertObserver());

        EventProcessor processor = new EventProcessor(publisher);
        processor.process(events);
    }
}
