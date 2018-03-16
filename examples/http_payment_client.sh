#!/bin/bash

rm ../payment_history.json

echo "================================================"
echo "Query non existing payment"
curl -v -X GET  -H "Accept: application/json" localhost:8080/users/123/payments/100000

echo "================================================"
echo "Create payment"
curl -v -X POST localhost:8080/users/123/payments -H "Content-Type: application/json" -d '{"provider":"paysafe", "amount":100, "currency":"EUR"}' | json_pp

echo "================================================"
echo "Query existing payment"
curl -v -X GET  -H "Accept: application/json" localhost:8080/users/123/payments/0 | json_pp
