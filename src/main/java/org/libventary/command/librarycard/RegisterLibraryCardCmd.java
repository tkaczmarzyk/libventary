package org.libventary.command.librarycard;

import java.util.UUID;


public class RegisterLibraryCardCmd {

    private final UUID libraryCardId;
    private final String cardholderName;
    
    public RegisterLibraryCardCmd(UUID id, String cardholderName) {
        this.libraryCardId = id;
        this.cardholderName = cardholderName;
    }
    
    public String getCardholderName() {
        return cardholderName;
    }
    
    public UUID getLibraryCardId() {
        return libraryCardId;
    }
}
