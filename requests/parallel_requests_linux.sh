#!/bin/bash

url="http://localhost:8080/orders"
data='{"userId": "a1b2c3d4-e5f6-11ec-8fea-0242ac120001", "productId": "f1e2d3c4-b5a6-11ec-9fea-0242ac120001", "quantity": 1}'

for i in {1..10}
do
  (
    curl -X POST -i "$url" \
         -H "Content-Type: application/json" \
         -d "$data" \
         -w "\nTotal time for request $i: %{time_total}s\n" \
         -o /dev/stdout -s
    echo ""
    echo ""
  ) &
done

wait
echo "Όλα τα αιτήματα ολοκληρώθηκαν."
