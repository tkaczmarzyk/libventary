package org.libventary.model.book;

import java.util.UUID;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.libventary.command.book.ReserveBookCmd;
import org.libventary.model.librarycard.LibraryCardRegistered;


public class BookTest {

    private FixtureConfiguration<Book> fixture;

    UUID id = UUID.randomUUID();
    UUID cardId = UUID.randomUUID();
    
    @Before
    public void setUp() {
        fixture = Fixtures.newGivenWhenThenFixture(Book.class);
//        BookCommandHandler commandHandler = new BookCommandHandler(fixture.getRepository());
//        fixture.registerAnnotatedCommandHandler(commandHandler);
    }
    
    //@formatter:off
    
    
    @Ignore // wspanialy relikt przeszlosci
    @Test
    public void successfullyReservesBook() {
        fixture
            .given(
                new NewBookAdded(id, "Nad Niemnem"),
                new LibraryCardRegistered(cardId, "Stefan vel 'Serverus'"))
            .when(
                new ReserveBookCmd(id, cardId))
            .expectEvents(
                new BookReserved(id, cardId));
    }
}
