package org.libventary.projection.reader;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reader {

    @Id
    private UUID id;
    
    private int numReserved;
    
    private int borrowingLimit;

    public void incrementNumReserved() {
        numReserved++;
    }
    
}
