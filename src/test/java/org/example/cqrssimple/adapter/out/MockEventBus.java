package org.example.cqrssimple.adapter.out;

import lombok.Getter;
import org.example.cqrssimple.domain.event.IDomainEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class MockEventBus extends InMemorySynchronousEventBus {
    List<IDomainEvent> sentEvents = new ArrayList<>();

    @Override
    public void send(Collection<IDomainEvent> domainEvents) {
        sentEvents.addAll(domainEvents);
        super.send(domainEvents);
    }
}
