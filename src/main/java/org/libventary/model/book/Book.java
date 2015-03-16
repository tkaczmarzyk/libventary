package org.libventary.model.book;

import java.util.UUID;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.libventary.model.librarycard.BorrowingLimitEstablished;


public class Book extends AbstractAnnotatedAggregateRoot<UUID> {
    
    private static final int INITIAL_BORROWING_LIMIT = 5;
    
    private static final long serialVersionUID = 1L;
    
    @AggregateIdentifier
    private UUID id;
    
    private boolean reserved;
    
    Book() {
    }
    
    public Book(UUID id, String title) {
        apply(new NewBookAdded(id, title));
        apply(new BorrowingLimitEstablished(id, INITIAL_BORROWING_LIMIT));
    }
    
    @EventHandler
    protected void handle(NewBookAdded event) {
        this.id = event.getBookId();
    }
    
    @EventHandler
    protected void handle(BookReserved bookBorrowed) {
        reserved = true;
    }

    public void reserveBy(UUID cardId) {
        if(reserved) {
            new BookAlreadyBorrowedException();
        }
        apply(new BookReserved(id, cardId));
    }
}
