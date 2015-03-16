package org.libventary.model.librarycard;

import java.util.UUID;


public class BookReservationFinalized {

    private final UUID libraryCardId;
    private final UUID bookId;
    
    public BookReservationFinalized(UUID libraryCardId, UUID bookId) {
        this.libraryCardId = libraryCardId;
        this.bookId = bookId;
    }
    
    public UUID getBookId() {
        return bookId;
    }
    
    public UUID getLibraryCardId() {
        return libraryCardId;
    }

    @Override
    public String toString() {
        return "BookReservationFinalized [libraryCardId=" + libraryCardId + ", bookId=" + bookId + "]";
    }
}
