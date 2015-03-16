package org.libventary.command.librarycard;

import javax.transaction.Transactional;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.libventary.model.librarycard.LibraryCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Transactional
public class LibraryCardCommandHandler {

    private final Repository<LibraryCard> repo;
    
    @Autowired
    public LibraryCardCommandHandler(Repository<LibraryCard> repo) {
        this.repo = repo;
    }
    
    @CommandHandler
    public void handle(RegisterLibraryCardCmd cmd) {
        LibraryCard card = new LibraryCard(cmd.getLibraryCardId(),cmd.getCardholderName());
        repo.add(card);
    }
    
    @CommandHandler
    public void handle(ConfirmBookReservationCmd cmd) {
        LibraryCard card = repo.load(cmd.getLibraryCardId());
        card.confirmReservation(cmd.getBookId());
    }
}
