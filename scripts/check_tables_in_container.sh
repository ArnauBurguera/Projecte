#!/usr/bin/env bash

# Database connection settings (from environment variables)
DB_HOST="localhost"                   # Connects to the mapped host port
DB_PORT="5401"                        # Host port mapped to PostgreSQL container port (5432) in docker-compose
DB_USER="${SPRING_DATASOURCE_USERNAME:-arnau}"
DB_PASSWORD="${SPRING_DATASOURCE_PASSWORD:-Aburguera1234}"
DB_NAME="${POSTGRES_DB:-projectedb}"

echo "[*] Checking database connection and listing tables..."

# this is a psql command to avoid prompt (introduce password) during `psql` execution
export PGPASSWORD=$DB_PASSWORD

# Attempt to connect to the database and list tables in one command
psql -h "$DB_HOST" -p "$DB_PORT" -U "$DB_USER" -d "$DB_NAME" -c '\dt' || {
    echo "[!] Unable to connect to the database. Please check your settings."
    exit 1
}

# Cleanup
unset PGPASSWORD # clears the password afterward for security reasons, removing it from the environment.

# about -c '\dt':
# -c: This flag in psql specifies that the following string is an SQL command to be executed directly.
# Instead of entering an interactive session with psql, it will execute the command given after -c and then exit.

# \dt: This is a psql meta-command (or shortcut command) that lists all tables in the currently connected
# database, including schema, table names, types, and ownership.