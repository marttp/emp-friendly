# Technical Design

## Tech Stack

- Spring Boot/WebFlux [Java/Kotlin] + Redis OM Spring
- Sanic [Python] + Redis OM Python
- Redis Stack (Cloud)
- Kubernetes (Deployment/Service/Ingress/Secret)

## Architecture

![Architecture](Technical%20High-Level%20Architecture.drawio.png)

- Ingress to reverse proxy to aggregator level
- Aggregator level - General (ordinary), Driver (for driver role), Management (for admin management)
- Internal service will communicate via Kubernetes namespace svc url
- Secret will store only Redis connection related

## Describe on each microservices

### Aggregator layer

- **General** - Spring WebFlux (Kotlin) + GraphQL

- **Driver** - Spring WebFlux (Java)

- **Management** - Spring WebFlux + GraphQL (Java)

### Internal layer

This layer will interact directly with Redis Cloud

- **Employee** - Spring WebFlux (Java) [Repository]

- **Product** - Spring WebFlux (Kotlin) [EntityStream]

- **Location** - Spring WebFlux (Java) [EntityStream]

- **Order** - Spring Boot (Kotlin) [Repository]

- **Payment** - Spring Boot (Kotlin) [Repository]

- **Shipment** - Spring Boot (Java) [EntityStream]

- **Point** - Spring Boot (Java) [Repository]

- **Rating** - Sanic

- **Restaurant Management** - Sanic

- **Notification** - Sanic

- **QR Code** - Sanic

- **Invoice** - FastAPI

- **Coupon** - FastAPI

## Service communication methodology

**Synchronous**: RESTful JSON (can minimize latency with gRPC)

**Asynchronous**: RedisStream (Stream, Pub/Sub)
