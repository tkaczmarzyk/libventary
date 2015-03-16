package org.libventary.command.book;

import javax.transaction.Transactional;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.libventary.model.book.Book;
import org.libventary.model.librarycard.LibraryCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class BookCommandHandler {

    private final Repository<Book> bookRepo;
    private final Repository<LibraryCard> libraryCardRepo;
    
    @Autowired
    public BookCommandHandler(Repository<Book> bookRepo, Repository<LibraryCard> libraryCardRepo) {
        this.bookRepo = bookRepo;
        this.libraryCardRepo = libraryCardRepo;
    }
    
    @CommandHandler
    public void handle(AddBookCmd cmd) {
        Book book = new Book(cmd.getBookId(), cmd.getTitle());
        bookRepo.add(book);
    }
    
    @CommandHandler
    public void handle(ReserveBookCmd cmd) {
        Book book = bookRepo.load(cmd.getBookId());
        book.reserveBy(cmd.getCardId());
    }

}
