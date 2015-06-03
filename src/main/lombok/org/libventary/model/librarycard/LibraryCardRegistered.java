package org.libventary.model.librarycard;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@Getter
@ToString
public class LibraryCardRegistered {

    private final UUID libraryCardId;
    private final String cardholderName;
}
