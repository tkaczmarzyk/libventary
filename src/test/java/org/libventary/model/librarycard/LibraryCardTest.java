package org.libventary.model.librarycard;

import java.util.UUID;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;
import org.libventary.command.librarycard.ConfirmBookReservationCmd;
import org.libventary.command.librarycard.LibraryCardCommandHandler;
import org.libventary.command.librarycard.RegisterLibraryCardCmd;


public class LibraryCardTest {

    private FixtureConfiguration<LibraryCard> fixture;

    UUID cardId = UUID.randomUUID();
    UUID bookId = UUID.randomUUID();
    
    @Before
    public void setUp() {
        fixture = Fixtures.newGivenWhenThenFixture(LibraryCard.class);
        LibraryCardCommandHandler commandHandler = new LibraryCardCommandHandler(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);
    }
    
    //@formatter:off
    
    @Test
    public void registersLibraryCardWithBorrowingLimit() {
        fixture
            .given()
            .when(
                new RegisterLibraryCardCmd(cardId, "Homer"))
            .expectEvents(
                new LibraryCardRegistered(cardId, "Homer"),
                new BorrowingLimitEstablished(cardId, 5));
    }
    
    @Test
    public void successfullyBorrowsBook() {
        fixture
            .given()
            .when(
                new RegisterLibraryCardCmd(cardId, "Homer"))
            .expectEvents(
                new LibraryCardRegistered(cardId, "Homer"),
                new BorrowingLimitEstablished(cardId, 5));
    }
    
    @Test
    public void confirmsBookReservation() {
        fixture
            .given(
                new LibraryCardRegistered(cardId, "Zią"))
            .when(
                new ConfirmBookReservationCmd(cardId, bookId))
            .expectEvents(
                new BookReservationFinalized(cardId, bookId));
            
    }
}
