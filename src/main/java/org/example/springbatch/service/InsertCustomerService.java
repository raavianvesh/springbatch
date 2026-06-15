package org.example.springbatch.service;

import org.example.springbatch.dto.Customers;
import org.example.springbatch.repository.CustomersRepository;
import org.springframework.stereotype.Service;

@Service
public class InsertCustomerService {

    private final CustomersRepository customersRepository;

    public InsertCustomerService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public void insertCustomerData(Customers customerData) {
        customersRepository.save((customerData));
    }
}
