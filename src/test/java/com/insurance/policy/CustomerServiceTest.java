package com.insurance.policy;

import com.insurance.policy.entity.Customer;
import com.insurance.policy.repository.CustomerRepository;
import com.insurance.policy.service.CustomerService;
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
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Smith");
        customer.setEmail("john@example.com");
        customer.setPhone("1234567890");
        customer.setAge(35);
    }

    @Test
    void testCreateCustomer() {
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer result = customerService.createCustomer(customer);
        assertNotNull(result);
        assertEquals("John Smith", result.getName());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    void testGetAllCustomers() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));
        List<Customer> result = customerService.getAllCustomers();
        assertEquals(1, result.size());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void testGetCustomerById() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        Customer result = customerService.getCustomerById(1L);
        assertNotNull(result);
        assertEquals("john@example.com", result.getEmail());
    }

    @Test
    void testGetCustomerById_NotFound() {
        when(customerRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> {
            customerService.getCustomerById(99L);
        });
    }

    @Test
    void testDeleteCustomer() {
        doNothing().when(customerRepository).deleteById(1L);
        customerService.deleteCustomer(1L);
        verify(customerRepository, times(1)).deleteById(1L);
    }
}