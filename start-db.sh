#!/bin/sh
set -eu

SCRIPT_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
COMPOSE_FILE="db/docker-compose.yaml"
COMPOSE_PATH="$SCRIPT_DIR/$COMPOSE_FILE"

if command -v docker.exe >/dev/null 2>&1; then
  DOCKER_BIN="docker.exe"
elif command -v docker >/dev/null 2>&1; then
  DOCKER_BIN="docker"
else
  echo "docker is not installed or not on PATH" >&2
  exit 1
fi

if ! "$DOCKER_BIN" info >/dev/null 2>&1; then
  echo "docker daemon is not running. Start Docker Desktop and try again." >&2
  exit 1
fi

if [ ! -f "$COMPOSE_PATH" ]; then
  echo "Compose file not found: $COMPOSE_PATH" >&2
  exit 1
fi

cd "$SCRIPT_DIR"

echo "Starting PostgreSQL and waiting for it to be healthy..."
"$DOCKER_BIN" compose -f "$COMPOSE_FILE" up -d --force-recreate --wait postgres
echo "PostgreSQL is available on localhost:5432"

echo "Starting pgAdmin..."
"$DOCKER_BIN" compose -f "$COMPOSE_FILE" up -d --force-recreate --wait pg-admin
echo "pgAdmin is available at http://localhost:5050"