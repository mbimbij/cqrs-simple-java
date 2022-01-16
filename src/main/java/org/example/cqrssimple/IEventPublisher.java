package org.example.cqrssimple;

import java.util.List;

public interface IEventPublisher {
    void publish(List<Event> events);
}
