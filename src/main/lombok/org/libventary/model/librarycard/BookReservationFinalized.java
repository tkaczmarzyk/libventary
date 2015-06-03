package org.libventary.model.librarycard;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@ToString
public class BookReservationFinalized {

    private final UUID libraryCardId;
    private final UUID bookId;
}
