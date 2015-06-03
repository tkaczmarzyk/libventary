package org.libventary.command.subscriptionplan;

import java.time.LocalDate;
import java.util.UUID;

import org.libventary.model.reader.Money;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DefineSubscriptionPlanCmd {

    private final UUID subscriptionPlanId;
    private final String name;
    private final Money price;
    private final Integer durationInDays;
    private final LocalDate activationDate;
    
}
