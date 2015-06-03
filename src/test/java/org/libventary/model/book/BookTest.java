package org.libventary.model.book;

import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.util.UUID;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.libventary.command.book.BookCommandHandler;
import org.libventary.command.book.ReserveBookCmd;
import org.libventary.model.reader.Address;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class BookTest {

    private FixtureConfiguration<Book> fixture;

    UUID readerId = UUID.randomUUID();
    UUID bookId = UUID.randomUUID();
    
    Address address = new Address("street", "4a", "wawa", "44-666");
    
    @Mock
    ReaderDomainService readerService;
    
    @Before
    public void setUp() {
        fixture = Fixtures.newGivenWhenThenFixture(Book.class);
        BookCommandHandler commandHandler = new BookCommandHandler(fixture.getRepository(), readerService);
        fixture.registerAnnotatedCommandHandler(commandHandler);
    }
    
    //@formatter:off
    
    @Test
    public void readerReservesABook() {
        
        given(readerService.isReservationLimitReached(readerId)).willReturn(false);
        
        fixture
            .given(
                    new BookAdded(bookId, "Nad Niemnem", "Eliza"))
            .when(
                    new ReserveBookCmd(bookId, readerId))
            .expectEvents(
                    new BookReserved(bookId, readerId, LocalDate.now(), LocalDate.now().plusDays(2)));
    }
    
    @Test
    public void readerCannotReserveABookAfterReachingHisReservationLimit() {
        
        given(readerService.isReservationLimitReached(readerId)).willReturn(true);
        
        fixture
            .given(
                    new BookAdded(bookId, "Nad Niemnem", "Eliza"))
            .when(
                    new ReserveBookCmd(bookId, readerId))
            .expectException(
                    ReservationLimitReachedException.class);
    }
}
