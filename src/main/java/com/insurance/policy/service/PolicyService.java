package com.insurance.policy.service;


import com.insurance.policy.event.PolicyCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import com.insurance.policy.entity.Policy;
import com.insurance.policy.entity.Customer;
import com.insurance.policy.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public Policy createPolicy(Long customerId, Policy policy) {
        Customer customer = customerService.getCustomerById(customerId);
        policy.setCustomer(customer);
        policy.setPremium(calculatePremium(customer.getAge(), policy.getPolicyType()));
        Policy savedPolicy = policyRepository.save(policy);
        eventPublisher.publishEvent(new PolicyCreatedEvent(this, savedPolicy));
        return savedPolicy;
    }

    public List<Policy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public Policy getPolicyById(Long id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Policy not found with id: " + id));
    }

    public List<Policy> getPoliciesByCustomer(Long customerId) {
        return policyRepository.findByCustomerId(customerId);
    }

    public List<Policy> getPoliciesByStatus(String status) {
        return policyRepository.findByStatus(status);
    }

    public Policy updatePolicyStatus(Long id, String status) {
        Policy policy = getPolicyById(id);
        policy.setStatus(status);
        return policyRepository.save(policy);
    }

    public void deletePolicy(Long id) {
        policyRepository.deleteById(id);
    }

    // Premium calculation business logic
    private Double calculatePremium(Integer age, String policyType) {
        double basePremium = 100.0;
        if (policyType.equals("HEALTH")) {
            basePremium = age > 40 ? 300.0 : 200.0;
        } else if (policyType.equals("LIFE")) {
            basePremium = age > 40 ? 500.0 : 350.0;
        }
        return basePremium;
    }
}