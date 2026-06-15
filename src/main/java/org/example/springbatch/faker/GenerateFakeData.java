package org.example.springbatch.faker;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class GenerateFakeData {

    private final Faker faker;

    public GenerateFakeData(Faker faker) {
        this.faker = faker;
    }

    public record Customer(UUID id, String name, String address, String phone, String email, Date dateOfBirth) {
    }

    public synchronized Customer generateCustomer(){
        return new Customer(UUID.randomUUID(),
                faker.name().fullName(),
                faker.address().fullAddress(),
                faker.phoneNumber().cellPhone(),
                faker.internet().emailAddress(),
                faker.date().birthday());
    }

    public void writeToFile(int numberOfRecordsToBeWritten, String fileName){
        try(BufferedWriter writer = Files.newBufferedWriter(Path.of(fileName))){
            Stream.generate(this::generateCustomer)
                    .limit(numberOfRecordsToBeWritten)
                    .map(customer -> String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",
                            customer.id().toString(),
                            customer.name(),
                            customer.address(),
                            customer.phone(),
                            customer.email(),
                            customer.dateOfBirth().toString()))
                    .forEach(line -> {
                        try {
                            writer.write(line);
                            writer.newLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
