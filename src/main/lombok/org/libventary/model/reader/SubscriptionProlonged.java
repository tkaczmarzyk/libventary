package org.libventary.model.reader;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class SubscriptionProlonged {

    private final UUID readerId;
    private final LocalDate dueDate;
}
