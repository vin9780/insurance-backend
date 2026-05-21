package com.insurance.policy;

import com.insurance.policy.entity.Customer;
import com.insurance.policy.entity.Policy;
import com.insurance.policy.repository.PolicyRepository;
import com.insurance.policy.service.CustomerService;
import com.insurance.policy.service.PolicyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PolicyServiceTest {

    @Mock
    private PolicyRepository policyRepository;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private PolicyService policyService;

    private Customer customer;
    private Policy policy;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Smith");
        customer.setEmail("john@example.com");
        customer.setAge(35);

        policy = new Policy();
        policy.setId(1L);
        policy.setPolicyNumber("POL-001");
        policy.setPolicyType("HEALTH");
        policy.setStatus("ACTIVE");
        policy.setCustomer(customer);
        policy.setPremium(200.0);
    }

    @Test
    void testCreatePolicy() {
        when(customerService.getCustomerById(1L)).thenReturn(customer);
        when(policyRepository.save(any(Policy.class))).thenReturn(policy);
        Policy result = policyService.createPolicy(1L, policy);
        assertNotNull(result);
        assertEquals("POL-001", result.getPolicyNumber());
        verify(policyRepository, times(1)).save(any(Policy.class));
    }

    @Test
    void testGetAllPolicies() {
        when(policyRepository.findAll()).thenReturn(Arrays.asList(policy));
        List<Policy> result = policyService.getAllPolicies();
        assertEquals(1, result.size());
        verify(policyRepository, times(1)).findAll();
    }

    @Test
    void testGetPolicyById() {
        when(policyRepository.findById(1L)).thenReturn(Optional.of(policy));
        Policy result = policyService.getPolicyById(1L);
        assertNotNull(result);
        assertEquals("HEALTH", result.getPolicyType());
    }

    @Test
    void testGetPolicyById_NotFound() {
        when(policyRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            policyService.getPolicyById(99L);
        });
    }

    @Test
    void testUpdatePolicyStatus() {
        when(policyRepository.findById(1L)).thenReturn(Optional.of(policy));
        when(policyRepository.save(any(Policy.class))).thenReturn(policy);
        Policy result = policyService.updatePolicyStatus(1L, "CANCELLED");
        assertEquals("CANCELLED", result.getStatus());
        verify(policyRepository, times(1)).save(any(Policy.class));
    }

    @Test
    void testPremiumCalculation_HealthUnder40() {
        when(customerService.getCustomerById(1L)).thenReturn(customer);
        when(policyRepository.save(any(Policy.class))).thenReturn(policy);
        policy.setPolicyType("HEALTH");
        customer.setAge(30);
        policyService.createPolicy(1L, policy);
        assertEquals(200.0, policy.getPremium());
    }

    @Test
    void testPremiumCalculation_HealthOver40() {
        customer.setAge(45);
        when(customerService.getCustomerById(1L)).thenReturn(customer);
        when(policyRepository.save(any(Policy.class))).thenReturn(policy);
        policy.setPolicyType("HEALTH");
        policyService.createPolicy(1L, policy);
        assertEquals(300.0, policy.getPremium());
    }
}