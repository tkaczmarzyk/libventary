package org.libventary.command.book;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CollectBookCmd {

    private final UUID bookId;
    private final UUID readerId;
    
}
