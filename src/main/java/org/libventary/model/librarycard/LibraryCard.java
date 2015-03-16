package org.libventary.model.librarycard;

import java.util.UUID;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;


public class LibraryCard extends AbstractAnnotatedAggregateRoot<UUID> {

    private static final int INITIAL_BORROWING_LIMIT = 5;
    
    private static final long serialVersionUID = 1L;
    
    @AggregateIdentifier
    private UUID id;
    
    LibraryCard() {
    }
    
    public LibraryCard(UUID id, String cardholderName) {
        apply(new LibraryCardRegistered(id, cardholderName));
        apply(new BorrowingLimitEstablished(id, INITIAL_BORROWING_LIMIT));
    }
    
    @EventHandler
    protected void handle(LibraryCardRegistered registeredEvent) {
        this.id = registeredEvent.getLibraryCardId();
    }
    
    public void confirmReservation(UUID bookId) {
//        if(){
//            throws
//        }
        apply(new BookReservationFinalized(this.id, bookId));
    }
    
    @EventHandler
    public void handle(BookReservationFinalized e) {
        System.out.println("DUpa");
    }
}
