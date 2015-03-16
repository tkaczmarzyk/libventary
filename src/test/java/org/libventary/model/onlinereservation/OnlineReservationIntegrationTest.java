package org.libventary.model.onlinereservation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.ObjectUtils;
import org.assertj.core.api.WithAssertions;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.domain.DomainEventMessage;
import org.axonframework.domain.DomainEventStream;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventstore.EventStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.libventary.Application;
import org.libventary.command.book.AddBookCmd;
import org.libventary.command.book.ReserveBookCmd;
import org.libventary.command.librarycard.RegisterLibraryCardCmd;
import org.libventary.model.book.Book;
import org.libventary.model.librarycard.LibraryCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Application.class)
public class OnlineReservationIntegrationTest implements WithAssertions {
    
    @Autowired
    CommandGateway cmdGateway;
    
    @Autowired
    EventStore eventStore;
    
    UUID cardId = UUID.randomUUID();
    UUID bookId = UUID.randomUUID();
    
    @Test
    public void onlineReservationSomethingSomething() {
        cmdGateway.sendAndWait(new RegisterLibraryCardCmd(cardId, "Siwy"));
        cmdGateway.sendAndWait(new AddBookCmd(bookId, "Nad Niemnem"));
        cmdGateway.sendAndWait(new ReserveBookCmd(bookId, cardId));
        
        DomainEventStream eventStreams = eventStore.readEvents(LibraryCard.class.getSimpleName(), cardId);
        List<DomainEventMessage<?>> events = new ArrayList<>();
        while (eventStreams.hasNext()) {
            events.add(eventStreams.next());
        }

        for (DomainEventMessage<?> event : events) {
            System.err.println(event.getPayloadType());
        }
        
//        DomainEventStream eventStreams = eventStore.readEvents(Book.class.getSimpleName(), bookId);
//        ArrayList<DomainEventMessage<?>> events = new ArrayList<>();
//        while (eventStreams.hasNext()) {
//            events.add(eventStreams.next());
//        }
//
//        for (DomainEventMessage<?> event : events) {
//            System.err.println(event.getPayloadType());
//        }
        
        assertThat(events).hasSize(3);
    }
}