package com.example.lab5q3.Controller;

import com.example.lab5q3.Model.Event;
import com.example.lab5q3.ResponseAPI.ResponseAPI;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/eventSystem")
public class EventController {

    // 1. Create a list of events
    ArrayList<Event> events = new ArrayList<Event>();

    // 2. Create a new event
    @PostMapping("/createEvent")
    public ResponseAPI createEvent(@RequestBody Event event) {
        events.add(event);
        return new ResponseAPI("New Event Created.");
    }

    // 3. Display all event
    @GetMapping("/getEvents")
    public ArrayList<Event> getEvents() {
        return events;
    }

    // 4. Update an event
    @PutMapping("/updateEvent/{index}")
    public ResponseAPI updateEvent(@PathVariable int index, @RequestBody Event event) {
        events.set(index, event);
        return new ResponseAPI("Event Updated.");
    }

    // 5. Delete an event
    @DeleteMapping("/deleteEvent/{index}")
    public ResponseAPI deleteEvent(@PathVariable int index) {
        events.remove(index);
        return new ResponseAPI("Event Deleted.");
    }

    // 6. Change capacity
    @PutMapping("/changeCapacity/{index}")
    public ResponseAPI changeCapacity(@PathVariable int index, @RequestParam int capacity) {
        events.get(index).setCapacity(capacity);
        return new ResponseAPI("Event Capacity Changed.");
    }

    // 7. Search for an event by given id
    @GetMapping("/searchEvent")
    public ResponseAPI searchEvent(@RequestParam String ID) {
        for (Event event : events) {
            if (event.getID().equals(ID)) {
                return new ResponseAPI("Event Found.");
            }
        }
        return new ResponseAPI("Event Not Found.");
    }
}