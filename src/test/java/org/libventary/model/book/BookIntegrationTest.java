package org.libventary.model.book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.*;

import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.axonframework.domain.DomainEventMessage;
import org.axonframework.domain.DomainEventStream;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.libventary.IntegrationTest;
import org.libventary.command.book.AddBookCmd;
import org.libventary.command.book.ReserveBookCmd;
import org.libventary.command.reader.RegisterReaderCmd;
import org.libventary.model.reader.Address;
import org.libventary.model.reader.BorrowingLimitEstablished;


public class BookIntegrationTest extends IntegrationTest {

    UUID readerId = UUID.randomUUID();
    UUID bookId = UUID.randomUUID();
    List<UUID> bookIds = Stream.generate(UUID::randomUUID).limit(6).collect(toList());
    Address address = new Address("street", "3a", "wrocke", "32033");

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    
    @Test
    public void readerReservesABook() {
        cmdGateway.sendAndWait(new RegisterReaderCmd(readerId, "Siwy", address));
        cmdGateway.sendAndWait(new AddBookCmd(bookId, "Nad Niemnem", "Orzeszkowa", 2));
        cmdGateway.sendAndWait(new ReserveBookCmd(bookId, readerId));

        assertThatBook(bookId).hasEvent(new BookReserved(bookId, readerId, LocalDate.now(), LocalDate.now().plusDays(2)));
    }

    @Test
    public void readerCannotReserveBookBeyondReservationLimit() {
        cmdGateway.sendAndWait(new RegisterReaderCmd(readerId, "Siwy", address));

        given6BooksAdded();
        given5BooksReserved(readerId);
        
        expectedException.expect(ReservationLimitReachedException.class);
        
        cmdGateway.sendAndWait(new ReserveBookCmd(bookIds.get(5), readerId));
        
    }

    private void given6BooksAdded() {
        for (UUID bookId : bookIds) {
            cmdGateway.sendAndWait(new AddBookCmd(bookId, "Nad Niemnem", "Orzeszkowa", 2));
        }
    }
    
    private void given5BooksReserved(UUID readerId) {
        for (int i = 0; i < 5; i++) {
            cmdGateway.sendAndWait(new ReserveBookCmd(bookIds.get(i), readerId));
        }
    }

    private EventsAssert assertThatBook(UUID bookId) {
        DomainEventStream eventStreams = eventStore.readEvents(Book.class.getSimpleName(), bookId);
        List<DomainEventMessage<?>> events = new ArrayList<>();
        while (eventStreams.hasNext()) {
            events.add(eventStreams.next());
        }
        return new EventsAssert(events.stream().map(msg -> msg.getPayload()));
    }

    private class EventsAssert {

        private List<Object> events;

        public EventsAssert(Stream<Object> events) {
            this.events = events.collect(toList());
        }

        public EventsAssert hasEvent(Object evnt) {
            Assertions.assertThat(events).contains(evnt);
            return this;
        }
    }
}
