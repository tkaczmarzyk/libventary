package org.libventary.model.book;

import java.util.UUID;


public interface ReaderDomainService {

    boolean isReservationLimitReached(UUID readerId);
}
