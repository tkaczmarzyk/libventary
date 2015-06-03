package org.libventary.command.subscriptionplan;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ActivateSubscriptionPlanCmd {

    private final UUID subscriptionPlanId;
    
}
