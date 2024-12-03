#!/bin/bash

if [ -z "$1" ]; then
  echo "Usage: $0 <day>"
  exit 1
fi

if [ -z "$AOC_SESSION" ]; then
  echo "Error: AOC_SESSION environment variable is not set."
  exit 1
fi

DAY=$1
URL="https://adventofcode.com/2024/day/${DAY}/input"
OUTPUT_FILE="src/main/resources/${DAY}.txt"

curl -H "Cookie: session=$AOC_SESSION" "$URL" > "$OUTPUT_FILE"

if [ $? -eq 0 ]; then
  echo "Input for day $DAY saved to $OUTPUT_FILE"
else
  echo "Failed to fetch input for day $DAY"
fi