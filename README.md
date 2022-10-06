# 01-lab-keycloak-markusremplbauer

This repo contains a keycloak-server using a postgres-db and uses traefik as a reverse proxy which secures the keycloak using Let's Encrypt.

## Keycloak

The Keycloak instance is reachable at: 
[https://markus.jakobrathberger.eu/](https://markus.jakobrathberger.eu/)

## Quarkus Service

You can test the functionality using the following http requests:

```http request
### obtain a token for the `alice` user

POST https://markus.jakobrathberger.eu/realms/quarkus/protocol/openid-connect/token
Authorization: Basic backend-service secret
Content-Type: application/x-www-form-urlencoded

username=alice&password=alice&grant_type=password

> {% client.global.set("access_token", response.body.access_token); %}

### use token to authorize for the `/api/users/me` endpoint
GET http://localhost:8080/api/users/me
Authorization: Bearer {{access_token}}

### use token to authorize as normal user for the `/api/users/admin` endpoint
GET http://localhost:8080/api/users/admin
Authorization: Bearer {{access_token}}

### obtain a token for the `admin` user

POST https://markus.jakobrathberger.eu/realms/quarkus/protocol/openid-connect/token
Authorization: Basic backend-service secret
Content-Type: application/x-www-form-urlencoded

username=admin&password=admin&grant_type=password

> {% client.global.set("access_token", response.body.access_token); %}

### use token to authorize as admin for the `/api/users/admin` endpoint
GET http://localhost:8080/api/users/admin
Authorization: Bearer {{access_token}}

```
