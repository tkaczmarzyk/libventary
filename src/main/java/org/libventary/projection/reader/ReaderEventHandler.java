package org.libventary.projection.reader;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.libventary.model.book.BookReserved;
import org.libventary.model.reader.BorrowingLimitEstablished;
import org.libventary.model.reader.ReaderRegistered;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ReaderEventHandler {

    private static final Logger log = LoggerFactory.getLogger(ReaderEventHandler.class);

    @Autowired
    private ReaderDao readerDao;

    
    @EventHandler
    public void handle(ReaderRegistered event) {
        readerDao.save(new Reader(event.getReaderId(), 0, 5));
    }
    
    @EventHandler
    public void handle(BorrowingLimitEstablished event) {
        Reader reader = readerDao.findOne(event.getReaderId());
        reader.setBorrowingLimit(event.getLimit());
        readerDao.save(reader);
    }
    
    @EventHandler
    public void handle(BookReserved event) {
        Reader reader = readerDao.findOne(event.getReaderId());
        reader.incrementNumReserved();
        readerDao.save(reader);
    }
}
