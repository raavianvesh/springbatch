package org.example.springbatch.load;

import org.example.springbatch.dto.Customers;
import org.example.springbatch.service.InsertCustomerService;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;

public class SimpleItemWriter implements ItemWriter<Customers> {

    private final InsertCustomerService insertCustomerService;

    public SimpleItemWriter(InsertCustomerService insertCustomerService) {
        this.insertCustomerService = insertCustomerService;
    }

    @Override
    public void write(Chunk<? extends Customers> chunk) {
        chunk.getItems().forEach(insertCustomerService::insertCustomerData);
    }
}
