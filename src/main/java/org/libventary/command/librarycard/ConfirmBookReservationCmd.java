package org.libventary.command.librarycard;

import java.util.UUID;


public class ConfirmBookReservationCmd {

    private final UUID libraryCardId;
    private final UUID bookId;
    
    public ConfirmBookReservationCmd(UUID libraryCardId, UUID bookId) {
        this.libraryCardId = libraryCardId;
        this.bookId = bookId;
    }
    
    public UUID getBookId() {
        return bookId;
    }
    
    public UUID getLibraryCardId() {
        return libraryCardId;
    }
}