package org.libventary.command.book;

import java.util.UUID;


public class ReserveBookCmd {

    private final UUID bookId;
    private final UUID cardId;
    
    public ReserveBookCmd(UUID bookId, UUID cardId) {
        this.bookId = bookId;
        this.cardId = cardId;
    }
    
    public UUID getBookId() {
        return bookId;
    }
    
    public UUID getCardId() {
        return cardId;
    }

}
