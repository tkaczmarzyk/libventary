package org.libventary.command.subscriptionplan;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeactivateSubscriptionPlanCmd {

    private final UUID subscriptionPlanId;
    
}
