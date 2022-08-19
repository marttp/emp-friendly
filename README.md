# EmpFriendly

EmpFriendly [Redis Hackathon on DEV 2022] - Support you employees and strive to be better

[Insert app screenshots](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax#uploading-assets)

# Overview video

Here's a short video that explains the project and how it uses Redis:

[Insert your own video here, and remove the one below]

[![Embed your YouTube video](https://i.ytimg.com/vi/vyxdC1qK4NE/maxresdefault.jpg)](https://www.youtube.com/watch?v=vyxdC1qK4NE)

## How it works

### How Architecture look like?

![Architecture](./architecture/Technical%20High-Level%20Architecture.drawio.png)

[More Informations](https://github.com/marttp/emp-friendly/tree/main/architecture) are inside the architecture directory

### How the data is stored:

Refer to [this example](https://github.com/redis-developer/basic-analytics-dashboard-redis-bitmaps-nodejs#how-the-data-is-stored) for a more detailed example of what you need for this section.

### How the data is accessed:

Refer to [this example](https://github.com/redis-developer/basic-analytics-dashboard-redis-bitmaps-nodejs#how-the-data-is-accessed) for a more detailed example of what you need for this section.

## How to run it locally?

[Make sure you test this with a fresh clone of your repo, these instructions will be used to judge your app.]

### Prerequisites

- Java 17 [Option1 - sdkman](https://sdkman.io/jdks) | [Option2 - Microsoft-OpenJDK](https://docs.microsoft.com/th-th/java/openjdk/download)
- [Python 3.10.4](https://www.python.org/downloads/)
- [Docker](https://www.docker.com/products/docker-desktop/)
- Kubernetes Cluster [Option1 - Kind](https://kind.sigs.k8s.io/) | [Option2 - minikube](https://minikube.sigs.k8s.io/docs/start/)
- [kubectl](https://kubernetes.io/docs/tasks/tools/)
- [Redis Cloud](https://redis.info/try-free-dev-to)

### Local installation

[Insert instructions for local installation]
### COMMAND
XGROUP CREATE location-stream-event location-stream-event $ MKSTREAM

## Deployment

To make deploys work, you need to create free account on [Redis Cloud](https://redis.info/try-free-dev-to)
