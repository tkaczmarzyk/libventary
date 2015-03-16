package org.libventary.model.librarycard;

import java.util.UUID;


public class BorrowingLimitEstablished {

    private final UUID libraryCardId;
    private final int limit;
    
    public BorrowingLimitEstablished(UUID id, int limit) {
        this.libraryCardId = id;
        this.limit = limit;
    }
    
    public UUID getLibraryCardId() {
        return libraryCardId;
    }
    
    public int getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return "BorrowingLimitEstablished [libraryCardId=" + libraryCardId + ", limit=" + limit + "]";
    }
}
