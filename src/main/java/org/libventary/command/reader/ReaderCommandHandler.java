package org.libventary.command.reader;

import javax.transaction.Transactional;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.libventary.command.reader.RegisterReaderCmd;
import org.libventary.model.reader.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Transactional
public class ReaderCommandHandler {

    private final Repository<Reader> repo;
    
    @Autowired
    public ReaderCommandHandler(Repository<Reader> repo) {
        this.repo = repo;
    }
    
    @CommandHandler
    public void handle(RegisterReaderCmd cmd) {
        Reader card = new Reader(cmd.getReaderId(), cmd.getName(), cmd.getAddress());
        repo.add(card);
    }
}
