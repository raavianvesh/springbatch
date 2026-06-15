package org.example.springbatch.extract;

import org.example.springbatch.faker.GenerateFakeData;
import org.example.springbatch.faker.GenerateFakeData.Customer;
import org.springframework.batch.infrastructure.item.ItemReader;

public class SimpleItemReader implements ItemReader<Customer> {

    private final GenerateFakeData generateFakeData;
    private final int totalRecords;
    private int currentRecord;

    public SimpleItemReader(GenerateFakeData generateFakeData, int totalRecords) {
        this.generateFakeData = generateFakeData;
        this.totalRecords = totalRecords;
    }

    @Override
    public synchronized Customer read() {
        if (currentRecord >= totalRecords) {
            return null;
        }

        currentRecord++;
        return generateFakeData.generateCustomer();
    }
}
