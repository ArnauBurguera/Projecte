#!/usr/bin/env bash

echo "[*] Setting up your Spring Boot project..."

# Navigate to project directory
cd "$(dirname "$0")/.." || exit

# Install dependencies (using Gradle)
echo "[*] Installing dependencies..."
./gradlew clean build

# Run tests
echo "[*] Running tests..."
./gradlew test

echo "[*] Setup complete. You can now run the application."