package org.libventary.model.book;

import java.util.UUID;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.libventary.command.librarycard.ReaderCommandHandler;
import org.libventary.model.librarycard.Reader;
import org.libventary.model.reader.Address;


public class BookTest {

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
    
    
}
