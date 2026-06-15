package org.example.springbatch.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Persistable;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
@Entity
public class Customers implements Persistable<UUID> {

    @Id
    @Column(name = "customer_id")
    private UUID id;
    @Column(name = "customer_name")
    private String name;
    @Column(name = "customer_address")
    private String address;
    @Column(name = "customer_phone")
    private String phone;
    @Column(name = "customer_email", unique = true)
    private String email;
    @Column(name = "customer_date_of_birth")
    private Date dateOfBirth;

    // A flag to explicitly track if the entity is brand new
    @Transient
    private boolean isNew = true;

    @Override
    public UUID getId() { return id; }

    @Override
    public boolean isNew() { return isNew; }

    // Use a lifecycle callback to flip the flag after it's saved
    @jakarta.persistence.PostLoad
    @jakarta.persistence.PostPersist
    void markNotNew() { this.isNew = false; }

}
