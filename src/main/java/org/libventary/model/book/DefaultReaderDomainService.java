package org.libventary.model.book;

import java.util.UUID;

import org.libventary.projection.reader.Reader;
import org.libventary.projection.reader.ReaderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DefaultReaderDomainService implements ReaderDomainService {

    @Autowired
    private ReaderDao readerDao;
    
    @Override
    public boolean isReservationLimitReached(UUID readerId) {
        Reader reader = readerDao.findOne(readerId);
        return reader.getNumReserved() >= reader.getBorrowingLimit();
    }

}
