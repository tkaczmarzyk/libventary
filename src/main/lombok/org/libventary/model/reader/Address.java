package org.libventary.model.reader;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Address {

    private final String street;
    private final String houseNumber;
    private final String city;
    private final String postalCode;
    
}
