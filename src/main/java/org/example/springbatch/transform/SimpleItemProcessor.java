package org.example.springbatch.transform;

import org.example.springbatch.dto.Customers;
import org.example.springbatch.faker.GenerateFakeData.Customer;
import org.example.springbatch.mapper.CustomerRecordToCustomerDto;
import org.jspecify.annotations.NonNull;
import org.springframework.batch.infrastructure.item.ItemProcessor;

public class SimpleItemProcessor implements ItemProcessor<Customer, Customers> {

    private final CustomerRecordToCustomerDto customerRecordToCustomerDto;

    public SimpleItemProcessor(CustomerRecordToCustomerDto customerRecordToCustomerDto) {
        this.customerRecordToCustomerDto = customerRecordToCustomerDto;
    }

    @Override
    public Customers process(@NonNull Customer customer) {
        System.out.printf("[Processor] Processing customer: %s on thread: %s%n", customer.name(), Thread.currentThread().getName());
        return customerRecordToCustomerDto.toCustomerDto(customer);
    }
}
