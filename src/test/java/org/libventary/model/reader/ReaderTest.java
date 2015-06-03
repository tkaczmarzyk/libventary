package org.libventary.model.reader;

import java.util.UUID;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;
import org.libventary.command.librarycard.ReaderCommandHandler;
import org.libventary.command.reader.RegisterReaderCmd;
import org.libventary.model.librarycard.Reader;


public class ReaderTest {

    private FixtureConfiguration<Reader> fixture;

    UUID cardId = UUID.randomUUID();
    UUID bookId = UUID.randomUUID();
    
    Address address = new Address("street", "4a", "wawa", "44-666");
    
    @Before
    public void setUp() {
        fixture = Fixtures.newGivenWhenThenFixture(Reader.class);
        ReaderCommandHandler commandHandler = new ReaderCommandHandler(fixture.getRepository());
        fixture.registerAnnotatedCommandHandler(commandHandler);
    }
    
    //@formatter:off
    
    @Test
    public void registersLibraryCardWithBorrowingLimit() {
        fixture
            .given()
            .when(
                new RegisterReaderCmd(cardId, "Homer", address))
            .expectEvents(
                new ReaderRegistered(cardId, "Homer", address),
                new BorrowingLimitEstablished(cardId, 5));
    }
    
    @Test
    public void successfullyBorrowsBook() {
        fixture
            .given()
            .when(
                new RegisterReaderCmd(cardId, "Homer", address))
            .expectEvents(
                new ReaderRegistered(cardId, "Homer", address),
                new BorrowingLimitEstablished(cardId, 5));
    }
}
