# Technical Design

## Tech Stack

- Spring Boot/WebFlux [Java/Kotlin] + Redis OM Spring
- Sanic, FastAPI [Python] + Redis OM Python
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

- [ ] **General** - Spring WebFlux (Kotlin) + GraphQL

- [ ] **Driver** - Spring WebFlux (Java)

- [ ] **Management** - Spring WebFlux + GraphQL (Java)

### Internal layer

This layer will interact directly with Redis Cloud
#### **[NOTICE] I can't use $ for EntitySteam with no reasons (Autowired, Constructor Injection) so I changeed plan to use only repository**

- [x] **Employee** - Spring WebFlux (Java) [Repository]

- ~~**Product** - Spring WebFlux (Kotlin) ~~[EntityStream]~~ [Repository]~~

- [x] **Location** - Spring WebFlux (Java) ~~[EntityStream]~~ [Repository]

- [ ] **Order** - Spring Boot (Kotlin) [Repository]

- [ ] **Payment** - Spring Boot (Kotlin) [Repository]

- [x] **Shipment** - Spring Boot (Java) **(Skeleton)**

- [x] **Point** - Spring Boot (Java) [Repository]

- [x] **Rating** - Sanic

- [ ] **Restaurant Management** - FastAPI

- [x] **Notification** - Sanic **(Skeleton)**

- [ ] **QR Code** - FastAPI

- [x] **Invoice** - FastAPI **(Skeleton)**

## Service communication methodology

**Synchronous**: RESTful JSON (can minimize latency with gRPC)

**Asynchronous**: RedisStream (Stream, Pub/Sub)
