package org.libventary.model.book;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ReaderLeftWaitingList {

    private final UUID bookId;
    private final UUID readerId;
}
