#!/usr/bin/env bash

# ./populate_users.sh 50 to generate 50 users
# Database connection settings
DB_HOST="localhost"
DB_PORT="5401"
DB_USER="${SPRING_DATASOURCE_USERNAME:-arnau}"
DB_PASSWORD="${SPRING_DATASOURCE_PASSWORD:-Aburguera1234}"
DB_NAME="${POSTGRES_DB:-projectedb}"

# Path to the Python script
PYTHON_SCRIPT="./generate_customers.py"

# Number of users to generate (default to 10 if not passed as argument)
NUM_USERS="${1}"

echo "[*] Starting user population script..."

# Step 1: Generate users with the Python script
echo "[*] Generating $NUM_USERS users using the Python script..."
# $(...): This syntax captures the standard output of the command inside the parentheses and assigns it to the variable GENERATED_USERS.
GENERATED_USERS=$(python3 "$PYTHON_SCRIPT" "$NUM_USERS") || {
    echo "[!] Failed to generate users. Exiting."
    exit 1
}

echo "[*] Users generated successfully."

# Step 2: Insert all generated users into the database in a single bulk operation
echo "[*] Preparing bulk insert statement..."

# Construct the bulk insert SQL command
# | tr '\n' ' ' | sed 's/, $//'); -> This command replaces all newline characters with spaces and removes the trailing comma and space.
BULK_INSERT="INSERT INTO customers (id, name, bank_account, role) VALUES $(echo "$GENERATED_USERS" | tr '\n' ' ' | sed 's/, $//');"

echo "[*] Executing bulk insert..."
# Export password to avoid prompt during `psql` execution
export PGPASSWORD=$DB_PASSWORD

# Execute the bulk insert statement
echo "$BULK_INSERT" | psql -h "$DB_HOST" -p "$DB_PORT" -U "$DB_USER" -d "$DB_NAME" || {
    echo "[!] Failed to insert users into the database."
    unset PGPASSWORD
    exit 1
}

# Cleanup
unset PGPASSWORD
echo "[*] User population complete."
