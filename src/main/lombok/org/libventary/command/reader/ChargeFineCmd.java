package org.libventary.command.reader;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.libventary.model.reader.Money;

@AllArgsConstructor
@Getter
public class ChargeFineCmd {

    private final UUID readerId;
    private final Money amount;
    
}
