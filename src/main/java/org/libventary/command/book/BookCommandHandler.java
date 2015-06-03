package org.libventary.command.book;

import javax.transaction.Transactional;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.libventary.model.book.Book;
import org.libventary.model.book.ReaderDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class BookCommandHandler {

    private final Repository<Book> bookRepo;
    private ReaderDomainService readerService;
    
    @Autowired
    public BookCommandHandler(Repository<Book> bookRepo, ReaderDomainService readerService) {
        this.bookRepo = bookRepo;
        this.readerService = readerService;
    }
    
    @CommandHandler
    public void handle(AddBookCmd cmd) {
        Book book = new Book(cmd.getBookId(), cmd.getTitle(), cmd.getAuthor());
        bookRepo.add(book);
    }
    
    @CommandHandler
    public void handle(ReserveBookCmd cmd) {
        Book book = bookRepo.load(cmd.getBookId());
        book.reserveBy(cmd.getReaderId(), readerService);
    }

}
