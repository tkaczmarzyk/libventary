package org.libventary.model.subscriptionplan;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.libventary.model.reader.Money;


@AllArgsConstructor
@Getter
public class SubscriptionPlanDefined {

    private final UUID subscriptionPlanId;
    private final String name;
    private final Money price;
    private final Integer durationInDays;
    private final LocalDate activationDate;
}
