#!/usr/bin/env bash

# Database connection settings (from your environment variables)
DB_HOST="localhost"
DB_PORT="5401"
DB_USER="${SPRING_DATASOURCE_USERNAME:-postgres}"
DB_PASSWORD="${SPRING_DATASOURCE_PASSWORD:-Aburguera1234}"
DB_NAME="${POSTGRES_DB:-postgres}"

echo "[*] Starting database reset..."

# Step 1: Export password to avoid prompt during `psql` execution
export PGPASSWORD=$DB_PASSWORD

# Step 2: Drop the database
echo "[*] Dropping the database: $DB_NAME"
psql -h "$DB_HOST" -p "$DB_PORT" -U "$DB_USER" -c "DROP DATABASE IF EXISTS $DB_NAME;" || {
    echo "[!] Failed to drop the database."
    unset PGPASSWORD
    exit 1
}

# Step 3: Recreate the database
echo "[*] Recreating the database: $DB_NAME"
psql -h "$DB_HOST" -p "$DB_PORT" -U "$DB_USER" -c "CREATE DATABASE $DB_NAME;" || {
    echo "[!] Failed to create the database."
    unset PGPASSWORD
    exit 1
}

# Step 4: Run Flyway migrations
echo "[*] Running Flyway migrations..."
./gradlew flywayMigrate || {
    echo "[!] Flyway migration failed."
    unset PGPASSWORD
    exit 1
}

# Cleanup
unset PGPASSWORD
echo "[*] Database reset and migrations complete."
