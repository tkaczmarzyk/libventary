package org.libventary.model.book;

import java.util.UUID;


public class BookReserved {

    private final UUID bookId;
    private final UUID libraryCardId;
    
    public BookReserved(UUID bookId, UUID libraryCardId) {
        this.bookId = bookId;
        this.libraryCardId = libraryCardId;
    }
    
    public UUID getBookId() {
        return bookId;
    }
    
    public UUID getLibraryCardId() {
        return libraryCardId;
    }
}
