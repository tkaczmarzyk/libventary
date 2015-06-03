package org.libventary.model.reader;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.apache.tomcat.jni.Address;

@AllArgsConstructor
@Getter
public class ReaderDetailsAmended {

    private final UUID readerId;
    private final String name;
    private final Address address;
}
