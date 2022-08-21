# EmpFriendly

EmpFriendly [Redis Hackathon on DEV 2022] - Support you employees and strive to be better

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

### [TODO] How the data is accessed:

Use Redis OM Spring and Redis OM Python as base libraries to work on

## How to run it locally?

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

### [TODO] Calling the API with Postman
Import Postman Collection: "" to your postman (or use cURL)

Including Aggregator Collection - General, Driver, Management related

### Prerequisites

Including development and deployments
- Java 17 [Option1 - sdkman](https://sdkman.io/jdks) | [Option2 - Microsoft-OpenJDK](https://docs.microsoft.com/th-th/java/openjdk/download)
- [Python 3.10.4](https://www.python.org/downloads/)
- [Docker](https://www.docker.com/products/docker-desktop/)
- Kubernetes Cluster [Option1 - Kind](https://kind.sigs.k8s.io/) | [Option2 - minikube](https://minikube.sigs.k8s.io/docs/start/)
- [kubectl](https://kubernetes.io/docs/tasks/tools/)
- [Redis Cloud](https://redis.info/try-free-dev-to)

### Local installation

For deployment purpose only

1. Install Kubernetes Cluster of choices
2. Follow "How to run it locally?" instructions

## Deployment

To make deploys work, you need to create free account on [Redis Cloud](https://redis.info/try-free-dev-to)
