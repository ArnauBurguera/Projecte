#!/bin/bash

# Database connection details
DB_HOST="localhost"
DB_PORT="5432"
DB_NAME="projectedb"
DB_USER="postgres"
DB_PASSWORD="Aburguera1234"

# SQL insertion commands
SQL_COMMANDS="
INSERT INTO customers (id, name, bank_account, role) VALUES
    ('3d23195c-5e29-4a5b-8cca-58ef6271f283', 'John Doe', '123456789', 'USER'),
    ('fd22f32e-f6c9-4a89-a07a-f80162d6c2c6', 'Jane Smith', '987654321', 'ADMIN'),
    -- Add more entries here
    ('51fcf616-36aa-48fb-8e40-bbe36bf9bfda', 'Eve Green', '000111222', 'ADMIN');
"

# Execute the SQL commands
PGPASSWORD=$DB_PASSWORD psql -h $DB_HOST -p $DB_PORT -d $DB_NAME -U $DB_USER -c "$SQL_COMMANDS"

echo "Customers inserted into the database."