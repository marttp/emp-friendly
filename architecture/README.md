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

- [ ] **General** - Spring WebFlux (Kotlin) + GraphQL :50200

- [ ] **Driver** - Spring WebFlux (Java) :50201

- [ ] **Management** - Spring WebFlux + GraphQL (Java) :50202

### Internal layer

This layer will interact directly with Redis Cloud
#### **[NOTICE] I can't use $ for EntitySteam with no reasons (Autowired, Constructor Injection) so I changeed plan to use only repository**

- [x] **Employee** - Spring WebFlux (Java) [Repository] :50203

- ~~**Product** - Spring WebFlux (Kotlin) ~~[EntityStream]~~ [Repository]~~

- [x] **Location** - Spring WebFlux (Java) ~~[EntityStream]~~ [Repository] :50204

- [ ] **Order** - Spring Boot (Kotlin) [Repository] :50206

- [ ] **Payment** - Spring Boot (Kotlin) [Repository] :50207

- [x] **Shipment** - Spring Boot (Java) **(Skeleton)**

- [x] **Point** - Spring Boot (Java) [Repository] :50205

- [x] **Rating** - Sanic :50209

- [x] **Restaurant Management** - FastAPI :50210

- [x] **Notification** - Sanic **(Skeleton)**

- [x] **QR Code** - Sanic :50208

- [x] **Invoice** - FastAPI **(Skeleton)**

## Service communication methodology

**Synchronous**: RESTful JSON (can minimize latency with gRPC)

**Asynchronous**: RedisStream (Stream, Pub/Sub)
