package org.libventary.model.reader;

import java.math.BigDecimal;

public class Money {

    private final BigDecimal amount;
    
    private Money(BigDecimal amount) {
        this.amount = amount;
    }
    
    public static Money valueOf(String amount) {
        return new Money(new BigDecimal(amount));
    }
    
}
