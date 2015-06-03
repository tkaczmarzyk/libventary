package org.libventary.command.reader;

import java.util.UUID;

import org.apache.tomcat.jni.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AmendReaderDetailsCmd {

    private final UUID readerId;
    private final String name;
    private final Address address;
    
}
