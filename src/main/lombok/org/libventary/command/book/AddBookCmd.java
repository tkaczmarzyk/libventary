package org.libventary.command.book;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class AddBookCmd {

    private final UUID bookId;
    private final String title;
    private final String author;
    private final Integer maxRentalDays;
    
}
