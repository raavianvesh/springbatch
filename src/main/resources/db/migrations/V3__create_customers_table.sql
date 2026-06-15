CREATE TABLE customers (
    customer_id UUID PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    customer_address VARCHAR(255) NOT NULL,
    customer_phone VARCHAR(255) NOT NULL,
    customer_email VARCHAR(255) NOT NULL,
    customer_date_of_birth TIMESTAMP NOT NULL,
    UNIQUE (customer_email)
);