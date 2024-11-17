#!/usr/bin/env bash

# Database connection settings (from your environment variables)
DB_HOST="localhost"
DB_PORT="5401"
DB_USER="${SPRING_DATASOURCE_USERNAME:-arnau}"
DB_PASSWORD="${SPRING_DATASOURCE_PASSWORD:-Aburguera1234}"
DB_NAME="${POSTGRES_DB:-projectedb}"

echo "[*] Starting database reset..."

# Step 1: Export password to avoid prompt during `psql` execution
export PGPASSWORD=$DB_PASSWORD

# Step 2: Drop the database
# -d postgres connects to the default database (postgres) to drop the database bc it cannot delete the db you're at.
echo "[*] Dropping the database: $DB_NAME"
psql -h "$DB_HOST" -p "$DB_PORT" -U "$DB_USER" -d postgres -c "DROP DATABASE IF EXISTS $DB_NAME;" || {
    echo "[!] Failed to drop the database."
    unset PGPASSWORD
    exit 1
}

# Step 3: Recreate the database
echo "[*] Recreating the database: $DB_NAME"
psql -h "$DB_HOST" -p "$DB_PORT" -U "$DB_USER" -d postgres -c "CREATE DATABASE $DB_NAME;" || {
    echo "[!] Failed to create the database."
    unset PGPASSWORD
    exit 1
}

# Step 4: Run Flyway migrations
# ../gradlew looks for gradlew in the parent directory (..), then --project-dir=.. specifies the project directory for gradle.
echo "[*] Running Flyway migrations..."
../gradlew --project-dir=.. flywayMigrate || {
    echo "[!] Flyway migration failed."
    unset PGPASSWORD
    exit 1
}

# Cleanup
unset PGPASSWORD
echo "[*] Database reset and migrations complete."
