package org.libventary.model.onlinereservation;

import java.util.UUID;

import org.axonframework.test.saga.AnnotatedSagaTestFixture;
import org.junit.Before;
import org.junit.Test;
import org.libventary.command.librarycard.ConfirmBookReservationCmd;
import org.libventary.model.book.BookReserved;
import org.libventary.model.librarycard.BookReservationFinalized;


public class OnlineReservationTest {

    private AnnotatedSagaTestFixture fixture;
    private UUID bookId;
    private UUID libraryCardId;
    
    @Before
    public void setup() {
        fixture = new AnnotatedSagaTestFixture(OnlineReservationSaga.class);
        bookId = UUID.randomUUID();
        libraryCardId = UUID.randomUUID();
    }
    
    @Test
    public void onlineReservationFlow() {
        fixture
            .givenNoPriorActivity()
            .whenAggregate(bookId)
            .publishes(new BookReserved(bookId,libraryCardId))
            .expectDispatchedCommandsEqualTo(new ConfirmBookReservationCmd(libraryCardId, bookId))
            .expectActiveSagas(1);

       fixture
           .givenAggregate(bookId)
           .published(new BookReserved(bookId,libraryCardId))
           .whenAggregate(libraryCardId)
           .publishes(new BookReservationFinalized(libraryCardId, bookId))
           .expectActiveSagas(0);
    }
    
}
