# EmpFriendly

EmpFriendly [Redis Hackathon on DEV 2022] - Support you employees and strive to be better

This project will show the overview of microservices architecture that design base on Cloud-Native approach (actually, some parts still missing because need to compete with the times), In this regards, I design to show how we can implement it from scratch including RESTful/GraphQL API, Aggregation Pattern, Asynchronous messaging with Pub/Sub and Streams.

[TODO] 

[Insert app screenshots](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#uploading-assets)

# [TODO] Overview video

Here's a short video that explains the project and how it uses Redis:

[Insert your own video here, and remove the one below]

[![Embed your YouTube video](https://i.ytimg.com/vi/vyxdC1qK4NE/maxresdefault.jpg)](https://www.youtube.com/watch?v=vyxdC1qK4NE)

## [TODO] How it works

### How Architecture look like?

![Architecture](./architecture/Technical%20High-Level%20Architecture.drawio.png)

[More Informations](https://github.com/marttp/emp-friendly/tree/main/architecture) are inside the architecture directory

### [TODO] How the data is stored:

Use Redis OM Spring and Redis OM Python as base libraries to work on
Below is JSON format of each document related
#### Employee

```json
{
  "id": "d37b2d0b-c06d-429c-b56d-7465c3959993",
  "firstName": "Thanaphoom",
  "lastName": "Babparn",
  "age": 25,
  "email": "thanaphoom.babparn@empfriendly.dev",
  "addressLoc": "100.7433723,14.0364895",
  "address": {
    "houseNumber": "109/1070",
    "city": "Thanyaburi",
    "state": "Pathum Thani",
    "postalCode": "12110",
    "country": "Thailand"
  },
  "tags": [
    "SOFTWARE_ENGINEER",
    "DEVOPS",
    "CLOUD_ENGINEER",
    "BACKEND_DEVELOPER"
  ],
  "type": "ORDINARY",
  "createdDate": 1660784666190
}
```

#### [TODO] Location

```json
```

#### Point

```json
{
  "referenceId":"d37b2d0b-c06d-429c-b56d-7465c3959993",
  "current": 500100,
  "type": "INDIVIDUAL"
}
```

#### Payment

```json
{
  "id": "01GAZDMREPWATKPJTB2N4M1CZ7",
  "from": "d37b2d0b-c06d-429c-b56d-7465c3959993",
  "to": "c820da4c-d8de-4229-848b-33e5f183a22c",
  "point": 120.0,
  "method": "QR_CODE_SCAN",
  "type": "INDIVIDUAL_DEBIT",
  "createdDate": 1661059359334
}
```

#### PointChangeHistory

```json
{
  "id": "01GAZDMSPKNQ9G3TQ298SM3D22",
  "referenceId": "d37b2d0b-c06d-429c-b56d-7465c3959993",
  "point": -120.0,
  "balancePoint": 500220.0,
  "createdDate": 1661059360527
}
```

==== SAME TRANSACTION ====

```json
{
  "id": "01GAZDMSQ1QW6QVX8CRH8FJ4B0",
  "referenceId": "c820da4c-d8de-4229-848b-33e5f183a22c",
  "point": 120.0,
  "balancePoint": 220.0,
  "createdDate": 1661059360580
}
```

#### [TODO] RatingHistory

```json
```

#### Restaurant

```json
{
  "pk": "01GAZ0HSPWKWA60J6CMQ11892G",
  "restaurant_id": "a3cbc4f1-3636-4db9-bb42-36c49ba655b9",
  "name": "SHOP-1"
}
```

#### Restaurant QR Code

```json
{
  "pk": "01GAZG9PHX6Q119B8EDXFQRJK5",
  "restaurant_id": "d31710fc-ba84-4ef9-ae2e-3c8e38e1c84c",
  "status": "active"
}
```

### How the data is accessed:

Use Redis OM Spring and Redis OM Python as base libraries to work on

#### Python Example - QR service

Use JsonModel to perform operation

```python
import datetime

from redis_om import (Field, JsonModel)

class QRCode(JsonModel):
    payment_id: str = Field(index=True)
    status: str = Field(index=True)
    created_date: datetime.datetime

class RestaurantQRCode(JsonModel):
    restaurant_id: str = Field(index=True)
    status: str = Field(index=True)
```

#### Java/Kotlin Example - EmployeeRepository

```java
package dev.tpcoder.empfriendly.employee.repository;

import com.redis.om.spring.repository.RedisDocumentRepository;
import dev.tpcoder.empfriendly.employee.model.Employee;
import java.util.Set;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends RedisDocumentRepository<Employee, String> {

  Iterable<Employee> findByAddressLocNear(Point point, Distance distance);

  Iterable<Employee> findByFirstNameAndLastName(String firstName, String lastName);

  Iterable<Employee> findByAddress_City(String city);

  Iterable<Employee> findByTags(Set<String> skills);

  Iterable<Employee> findByTagsContainingAll(Set<String> skills);

  Iterable<Employee> findByType(String employeeType);

  Iterable<Employee> search(String text);
}
```

## How to run it locally?

### Prerequisites

Including development and deployments

- Java 17 [Option1 - sdkman](https://sdkman.io/jdks) | [Option2 - Microsoft-OpenJDK](https://docs.microsoft.com/th-th/java/openjdk/download)
- [Python 3.10.4](https://www.python.org/downloads/)
- [Docker](https://www.docker.com/products/docker-desktop/)
- Kubernetes Cluster [Option1 - Kind](https://kind.sigs.k8s.io/) | [Option2 - minikube](https://minikube.sigs.k8s.io/docs/start/)
- [kubectl](https://kubernetes.io/docs/tasks/tools/)
- [Redis Cloud](https://redis.info/try-free-dev-to)
- [Postman](https://www.postman.com/)

### Local installation

For deployment purpose only

1. Install Kubernetes Cluster of choices
2. Create Database in [Redis Cloud](https://redis.info/try-free-dev-to)
3. Follow "How to run it locally?" instructions

### Transform Redis Cloud URL to base64

Note: the redis url format will be redis://{USERNAME}:{PASSWORD}@{REDIS_HOST_URL}:{REDIS_PORT}

```bash
echo 'redis://{USERNAME}:{PASSWORD}@{REDIS_HOST_URL}:{REDIS_PORT}' | base64
```

### Replace your REDIS URL in redis-sc.yaml

After you got Base64 txt data, replace it to redis-sc.yaml

```bash
REDIS_OM_URL: <YOUR_REDIS_URL in base64>
to
REDIS_OM_URL: BASE64_RESULT
```

### Add Stream Group in RedisInsight

```bash
XGROUP CREATE location-stream-event location-stream-event $ MKSTREAM
```

### Start Microservices with Kubernetes Cluster

```bash
kubectl apply -f ./k8s
```

### [TODO] Port Forward Service (Required 3 Terminals)

### Calling the API with Postman

Import Postman Collection: **RedisHackathonDev2022.postman_collection.json** to your postman (or use cURL)

Including Aggregator Collection - General, Driver, Management related

## Deployment

To make deploys work, you need to create free account on [Redis Cloud](https://redis.info/try-free-dev-to)
