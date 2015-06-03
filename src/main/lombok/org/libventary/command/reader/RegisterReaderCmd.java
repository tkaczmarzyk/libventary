package org.libventary.command.reader;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.libventary.model.reader.Address;

@AllArgsConstructor
@Getter
public class RegisterReaderCmd {

    private final UUID readerId;
    private final String name;
    private final Address address;
    
}
