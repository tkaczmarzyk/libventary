package org.libventary.command.book;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReturnBookCmd {

    private final UUID bookId;
    
}
