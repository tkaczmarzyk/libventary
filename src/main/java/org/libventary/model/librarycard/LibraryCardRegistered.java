package org.libventary.model.librarycard;

import java.util.UUID;


public class LibraryCardRegistered {

    private final UUID libraryCardId;
    private final String cardholderName;
    
    public LibraryCardRegistered(UUID id, String cardholderName) {
        this.libraryCardId = id;
        this.cardholderName = cardholderName;
    }
    
    public String getCardholderName() {
        return cardholderName;
    }
    
    public UUID getLibraryCardId() {
        return libraryCardId;
    }

    @Override
    public String toString() {
        return "LibraryCardRegistered [libraryCardId=" + libraryCardId + ", cardholderName=" + cardholderName + "]";
    }

}
