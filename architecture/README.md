# Technical Design

## Tech Stack

- Spring Boot/WebFlux [Java/Kotlin] + Redis OM Spring
- Sanic [Python] + Redis OM Python
- Redis Stack (Cloud)
- Kubernetes (Deployment/Service/Ingress/Secret)

---

## Architecture

![Architecture](Technical%20High-Level%20Architecture.drawio.png)

- Ingress to reverse proxy to aggregator level
- Aggregator level - General (ordinary), Driver (for driver role), Management (for admin management)
- Internal service will communicate via Kubernetes namespace svc url
- Secret will store only Redis connection related

---

## Describe on each microservices

### Aggregator layer

- **General** - Spring WebFlux (Kotlin)

- **Driver** - Spring WebFlux (Java)

- **Management** - Spring WebFlux + GraphQL (Java)

### Internal layer

- **Employee** - Spring WebFlux (Java)

- **Product** - Spring WebFlux (Kotlin)

- **Location** - Spring WebFlux (Java)

- **Order** - Spring Boot (Kotlin)

- **Payment** - Spring Boot (Kotlin)

- **Shipment** - Spring Boot (Java)

- **Point** - Spring Boot (Java)

- **Rating** - Sanic

- **Restaurant Management** - Sanic

- **Notification** - Sanic

- **QR Code** - Sanic

- **Invoice** - Sanic

- **Coupon** - Sanic

---

## Service communication methodology

**Synchronous**: RESTful JSON (can minimize latency with gRPC)

**Asynchronous**: RedisStream (Stream, Pub/Sub)
