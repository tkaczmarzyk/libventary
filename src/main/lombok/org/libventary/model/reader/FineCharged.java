package org.libventary.model.reader;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class FineCharged {

    private final UUID readerId;
    private final Money amountPaid;
}
