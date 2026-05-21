package com.insurance.policy.controller;

import com.insurance.policy.entity.Policy;
import com.insurance.policy.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/policies")
@CrossOrigin(origins = "*")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<Policy> createPolicy(@PathVariable Long customerId, @RequestBody Policy policy) {
        return ResponseEntity.ok(policyService.createPolicy(customerId, policy));
    }

    @GetMapping
    public ResponseEntity<List<Policy>> getAllPolicies() {
        return ResponseEntity.ok(policyService.getAllPolicies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Policy> getPolicyById(@PathVariable Long id) {
        return ResponseEntity.ok(policyService.getPolicyById(id));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Policy>> getPoliciesByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(policyService.getPoliciesByCustomer(customerId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Policy>> getPoliciesByStatus(@PathVariable String status) {
        return ResponseEntity.ok(policyService.getPoliciesByStatus(status));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Policy> updatePolicyStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(policyService.updatePolicyStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicy(@PathVariable Long id) {
        policyService.deletePolicy(id);
        return ResponseEntity.noContent().build();
    }
}