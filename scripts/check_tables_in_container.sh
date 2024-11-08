#!/usr/bin/env bash

# Database connection settings (from your environment variables)
DB_HOST="localhost"                   # Connects to the mapped host port
DB_PORT="5401"                        # Host port mapped to PostgreSQL in docker-compose
DB_USER="${SPRING_DATASOURCE_USERNAME:-postgres}"
DB_PASSWORD="${SPRING_DATASOURCE_PASSWORD:-Aburguera1234}"
DB_NAME="${POSTGRES_DB:-postgres}"

echo "[*] Checking database connection and listing tables..."

# Export password to avoid prompt during `psql` execution
export PGPASSWORD=$DB_PASSWORD

# Attempt to connect to the database and list tables in one command
psql -h "$DB_HOST" -p "$DB_PORT" -U "$DB_USER" -d "$DB_NAME" -c '\dt' || {
    echo "[!] Unable to connect to the database. Please check your settings."
    exit 1
}

# Cleanup
unset PGPASSWORD