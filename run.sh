#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
OUT_DIR="$ROOT_DIR/out"

if [ ! -d "$OUT_DIR" ]; then
  echo "Compiled classes not found. Run ./compile.sh first."
  exit 1
fi

cd "$ROOT_DIR"
java -cp "$OUT_DIR" taskcli.TaskCli "$@"
