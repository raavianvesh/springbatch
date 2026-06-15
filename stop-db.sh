#!/bin/sh
set -eu

SCRIPT_DIR=$(unset CDPATH; cd -- "$(dirname -- "$0")" && pwd)
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

echo "Stopping PostgreSQL and pgAdmin, removing containers and volumes..."
"$DOCKER_BIN" compose -f "$COMPOSE_FILE" down --volumes --remove-orphans
echo "PostgreSQL and pgAdmin containers have been removed. Volumes have also been removed."