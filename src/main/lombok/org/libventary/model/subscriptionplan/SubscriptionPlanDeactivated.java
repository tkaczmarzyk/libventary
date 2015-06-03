package org.libventary.model.subscriptionplan;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class SubscriptionPlanDeactivated {

    private final UUID subscriptionPlanId;
}
