package org.libventary.model.reader;

import java.util.UUID;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.libventary.model.reader.Address;
import org.libventary.model.reader.BookReservationFinalized;
import org.libventary.model.reader.BorrowingLimitEstablished;
import org.libventary.model.reader.ReaderRegistered;


public class Reader extends AbstractAnnotatedAggregateRoot<UUID> {

    private static final int INITIAL_BORROWING_LIMIT = 5;
    
    private static final long serialVersionUID = 1L;
    
    @AggregateIdentifier
    private UUID id;
    
    Reader() {
    }
    
    public Reader(UUID id, String name, Address address) {
        apply(new ReaderRegistered(id, name, address));
        apply(new BorrowingLimitEstablished(id, INITIAL_BORROWING_LIMIT));
    }
    
    @EventHandler
    protected void handle(ReaderRegistered registeredEvent) {
        this.id = registeredEvent.getReaderId();
    }
    
    public void confirmReservation(UUID bookId) {
        apply(new BookReservationFinalized(this.id, bookId));
    }
    
    @EventHandler
    public void handle(BookReservationFinalized e) {
        System.out.println("DUpa");
    }
}
