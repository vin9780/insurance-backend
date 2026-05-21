package com.insurance.policy.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class PolicyEventListener {

    @EventListener
    public void handlePolicyCreated(PolicyCreatedEvent event) {
        System.out.println("🎉 New Policy Created: "
                + event.getPolicy().getPolicyNumber()
                + " | Type: " + event.getPolicy().getPolicyType()
                + " | Premium: $" + event.getPolicy().getPremium());
    }
}