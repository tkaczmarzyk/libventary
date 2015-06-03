package org.libventary.model.book;

import java.util.UUID;

import org.springframework.stereotype.Service;


@Service
public class DefaultReaderDomainService implements ReaderDomainService {

    @Override
    public boolean isReservationLimitReached(UUID readerId) {
        return false;
    }

}
