package org.libventary.command.librarycard;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ConfirmBookReservationCmd {

    private final UUID libraryCardId;
    private final UUID bookId;
}