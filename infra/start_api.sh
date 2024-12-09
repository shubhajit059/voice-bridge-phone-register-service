#!/bin/bash

# Check that exactly two arguments are passed
if [ "$#" -ne 2 ]; then
  echo "Wrong arguments: $0 <env> <profile> , Available profiles are local, test and env are cmd and query"
  exit 1
fi

# Assign parameters to variables
env=$1
profile=$2

case $env in
  "cmd"|"query")
    echo "environment is valid: $env"
    ;;
  *)
    echo "Available environment is cmd or query."
    exit 1
    ;;
esac

case $profile in
  "local"|"test"|"prod")
    echo "profile is valid: $profile"
    ;;
  *)
    echo "Available profile is local or test or prod."
    exit 1
    ;;
esac

docker-compose -f docker-compose.yml -f docker-compose-$profile.yml --profile $env up --build -d
echo "API started successfully"