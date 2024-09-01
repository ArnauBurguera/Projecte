CREATE TYPE customer_role AS ENUM ('USER', 'ADMIN', 'MODERATOR');

CREATE TABLE customers (
   id UUID PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   bank_account VARCHAR(255) NOT NULL,
   role customer_role NOT NULL
);