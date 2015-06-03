package org.libventary.command.reader;

import java.util.UUID;

import org.libventary.model.reader.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterReaderCmd {

    private final UUID readerId;
    private final String name;
    private final Address address;
    
}
