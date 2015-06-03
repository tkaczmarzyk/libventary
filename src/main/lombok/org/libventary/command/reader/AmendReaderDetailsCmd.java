package org.libventary.command.reader;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.apache.tomcat.jni.Address;

@AllArgsConstructor
@Getter
public class AmendReaderDetailsCmd {

    private final UUID readerId;
    private final String name;
    private final Address address;
    
}
