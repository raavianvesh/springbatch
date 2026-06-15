package org.example.springbatch.mapper;

import org.example.springbatch.dto.Customers;
import org.example.springbatch.faker.GenerateFakeData.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerRecordToCustomerDto {

        Customers toCustomerDto(Customer customerRecord);
}
