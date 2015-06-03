package org.libventary.model.book;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class BookReserved {

    private final UUID bookId;
    private final UUID readerId;
    private final LocalDate fromDate;
    private final LocalDate dueDate;
}
