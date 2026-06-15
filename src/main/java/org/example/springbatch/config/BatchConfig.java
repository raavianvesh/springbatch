package org.example.springbatch.config;

import org.example.springbatch.dto.Customers;
import org.example.springbatch.extract.SimpleItemReader;
import org.example.springbatch.faker.GenerateFakeData;
import org.example.springbatch.faker.GenerateFakeData.Customer;
import org.example.springbatch.load.SimpleItemWriter;
import org.example.springbatch.mapper.CustomerRecordToCustomerDto;
import org.example.springbatch.partition.CustomPartitioner;
import org.example.springbatch.service.InsertCustomerService;
import org.example.springbatch.transform.SimpleItemProcessor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.partition.Partitioner;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("customerDataJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager,
                     ItemReader<Customer> reader, ItemProcessor<Customer, Customers> processor,
                     ItemWriter<Customers> writer, AsyncTaskExecutor taskExecutor) {
        return new StepBuilder("customerDataStep", jobRepository)
                .<Customer, Customers>chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .transactionManager(platformTransactionManager)
                .taskExecutor(taskExecutor)
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<Customer> reader(GenerateFakeData generateFakeData,
                                       @Value("${app.fake-data.total-records}") int totalRecords) {
        return new SimpleItemReader(generateFakeData, totalRecords);
    }

    @Bean
    public ItemProcessor<Customer, Customers> processor(CustomerRecordToCustomerDto customerRecordToCustomerDto) {
        return new SimpleItemProcessor(customerRecordToCustomerDto);
    }

    @Bean
    public ItemWriter<Customers> writer(InsertCustomerService insertCustomerService) {
        return new SimpleItemWriter(insertCustomerService);
    }

    @Bean
    public AsyncTaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor("batch-thread-");
    }

    @Bean
    public Partitioner partitioner() {
        return new CustomPartitioner();
    }
}
