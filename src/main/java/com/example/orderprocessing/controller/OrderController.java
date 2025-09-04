// package com.example.orderprocessing.controller;

// public class OrderController {
    
// }



// package com.example.orderprocessing.controller;

// import com.example.orderprocessing.event.Event;
// import com.example.orderprocessing.ingest.EventReader;
// import com.example.orderprocessing.processor.EventProcessor;
// import com.example.orderprocessing.observer.AlertObserver;
// import com.example.orderprocessing.observer.LoggerObserver;
// import org.springframework.web.bind.annotation.*;
// import java.util.*;

// @RestController
// @RequestMapping("/api/orders")
// public class OrderController {

//     private final EventProcessor processor;

//     public OrderController() {
//         this.processor = new EventProcessor();
//         this.processor.registerObserver(new LoggerObserver());
//         this.processor.registerObserver(new AlertObserver());
//     }

//     @PostMapping("/process")
//     public Map<String, Object> processEvents(@RequestBody List<Event> events) {
//         for (Event e : events) {
//             processor.processEvent(e);
//         }
//         Map<String, Object> response = new HashMap<>();
//         response.put("message", "Processed " + events.size() + " events.");
//         response.put("orders", processor.getOrders().values());
//         return response;
//     }

//     @GetMapping
//     public Collection<?> getAllOrders() {
//         return processor.getOrders().values();
//     }
// }
