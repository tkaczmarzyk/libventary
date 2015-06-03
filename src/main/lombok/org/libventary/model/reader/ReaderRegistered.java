package org.libventary.model.reader;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ReaderRegistered {

    private final UUID readerId;
    private final String name;
    private final Address address;
}
