#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
OUT_DIR="${1:-$ROOT_DIR/out}"

mkdir -p "$OUT_DIR"

JAVA_FILES=()
while IFS= read -r file; do
  JAVA_FILES+=("$file")
done < <(find "$ROOT_DIR/src/main/java" -name '*.java' | sort)

if [ "${#JAVA_FILES[@]}" -eq 0 ]; then
  echo "No Java source files found."
  exit 1
fi

javac -d "$OUT_DIR" "${JAVA_FILES[@]}"

echo "Compiled classes to: $OUT_DIR"
