package org.libventary.model.reader;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@ToString
public class BorrowingLimitEstablished {

    private final UUID readerId;
    private final int limit;
}
