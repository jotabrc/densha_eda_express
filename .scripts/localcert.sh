#!/bin/bash

cd ../.certs

openssl genrsa -out ca.key 4096
openssl req -new -x509 -days 365 -key ca.key -out ca.crt -subj "/CN=PostgreSQL Local CA"

openssl genrsa -out server.key 4096

openssl req -new -key server.key -out server.csr -subj "/CN=localhost" -addext "subjectAltName=DNS:localhost,DNS:*.localhost,IP:127.0.0.1"

openssl x509 -req -in server.csr -CA ca.crt -CAkey ca.key -CAcreateserial -out server.crt -days 365 -sha256

rm server.csr

chmod 644 ca.key server.key ca.crt server.crt