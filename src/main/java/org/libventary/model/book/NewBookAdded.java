package org.libventary.model.book;

import java.util.UUID;


public class NewBookAdded {

    private final UUID bookId;
    private final String title;
    
    public NewBookAdded(UUID bookId, String title) {
        this.bookId = bookId;
        this.title = title;
    }

    public UUID getBookId() {
        return bookId;
    }
    
    public String getTitle() {
        return title;
    }
}
