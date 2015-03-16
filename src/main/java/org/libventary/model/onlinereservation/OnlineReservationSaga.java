package org.libventary.model.onlinereservation;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.EndSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.libventary.command.librarycard.ConfirmBookReservationCmd;
import org.libventary.model.book.BookReserved;
import org.libventary.model.librarycard.BookReservationFinalized;
import org.springframework.beans.factory.annotation.Autowired;



public class OnlineReservationSaga extends AbstractAnnotatedSaga {

    private static final long serialVersionUID = 1L;
    
    @Autowired
    private transient CommandGateway commandGateway;
    
    @StartSaga
    @SagaEventHandler(associationProperty = "bookId")
    public void handle(BookReserved event) {
        ConfirmBookReservationCmd cmd = new ConfirmBookReservationCmd(event.getLibraryCardId(), event.getBookId());
        associateWith("libraryCardId", event.getLibraryCardId().toString());
        commandGateway.send(cmd);
    }
    
    @EndSaga
    @SagaEventHandler(associationProperty = "libraryCardId")
    public void handle(BookReservationFinalized cmd) {
        System.out.println("tu");
    }
    
    public void setCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }
}
