#!/bin/bash

if [ -z "$1" ]; then
  echo "Usage: $0 <day>"
  exit 1
fi

DAY=$1
URL="https://adventofcode.com/2024/day/${DAY}/input"
OUTPUT_FILE="src/main/resources/${DAY}.txt"
COOKIE="session=53616c7465645f5fcaa6fe9177b91e837323abea3a415841bcb91be7019df6979082804edb95901d303f485dfea5892f39749e77e6a1e0e5a10cfdf70718330e"

curl -H "Cookie: $COOKIE" "$URL" > "$OUTPUT_FILE"

if [ $? -eq 0 ]; then
  echo "Input for day $DAY saved to $OUTPUT_FILE"
else
  echo "Failed to fetch input for day $DAY"
fi