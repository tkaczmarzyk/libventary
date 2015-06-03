package org.libventary.model.book;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class BookReturned {

    private final UUID bookId;
    private final LocalDate collectionDate;
}
