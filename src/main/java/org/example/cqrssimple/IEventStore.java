package org.example.cqrssimple;

import java.util.List;

public interface IEventStore {
    void store(List<Event> events);
}
