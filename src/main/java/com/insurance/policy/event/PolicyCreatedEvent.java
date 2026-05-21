package com.insurance.policy.event;

import com.insurance.policy.entity.Policy;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class PolicyCreatedEvent extends ApplicationEvent {

    private final Policy policy;

    public PolicyCreatedEvent(Object source, Policy policy) {
        super(source);
        this.policy = policy;
    }

}