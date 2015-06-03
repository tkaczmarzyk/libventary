package org.libventary.model.book;

import java.time.LocalDate;
import java.util.UUID;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;


public class Book extends AbstractAnnotatedAggregateRoot<UUID> {
    
    private static final long serialVersionUID = 1L;

    private static final int RESERVATION_TIME_DAYS = 2;
    
    @AggregateIdentifier
    private UUID id;
    
    private boolean reserved;
    
    Book() {
    }
    
    public Book(UUID id, String title, String author) {
        apply(new BookAdded(id, title, author));
    }
    
    @EventHandler
    protected void handle(BookAdded event) {
        this.id = event.getBookId();
    }
    
    @EventHandler
    protected void handle(BookReserved bookReserved) {
        reserved = true;
    }

    public void reserveBy(UUID readerId, ReaderDomainService readerService) {
        if (!readerService.isReservationLimitReached(readerId)) {
            apply(new BookReserved(id, readerId, LocalDate.now(), LocalDate.now().plusDays(2)));
        } else {
            throw new ReservationLimitReachedException();
        }
    }
}
