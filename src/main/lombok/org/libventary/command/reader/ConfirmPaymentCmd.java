package org.libventary.command.reader;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConfirmPaymentCmd {

    private final UUID readerId;
    
}
