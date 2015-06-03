package org.libventary.command.book;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProlongBookRentalCmd {

    private final UUID bookId;
    private final Integer numDays;
    
}
