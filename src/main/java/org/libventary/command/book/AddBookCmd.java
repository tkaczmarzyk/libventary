package org.libventary.command.book;

import java.util.UUID;


public class AddBookCmd {

    private UUID bookId;
    private String title;
    
    public AddBookCmd(UUID bookId, String title) {
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
