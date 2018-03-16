#!/bin/bash

echo "================================================"
echo "Create paypal payment"
curl -X POST localhost:8080/users/123/payments -H "Content-Type: application/json" -d '{"provider":"paypal", "amount":50, "currency":"EUR"}' | json_pp

echo "================================================"
echo "Create paysafe payment"
curl -X POST localhost:8080/users/123/payments -H "Content-Type: application/json" -d '{"provider":"paysafe", "amount":150, "currency":"EUR"}' | json_pp
