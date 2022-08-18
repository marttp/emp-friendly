# Technical Informations

## Version

- Java 17
- Python 3.10.4
- Spring Boot: Internal service [2.6.6], Aggregator layer [2.7.2]
- Sanic 22.6 (venv)
- Container: Jib (Spring) + own Dockerfile (Sanic)

## Related Commands

### Gradle

Build container image

```bash
./gradlew jib --image=<YOUR_IMAGE_TARGET>
```

### Python

Create Virtual Environment

```bash
python3 -m venv venv #Linux/macOS
python -m venv venv #Windows
```

Active Virtual Environment

```bash
source ./venv/bin/activate #Linux/macOS
.\venv\Scripts\activate #Windows
```

Install Libraries from requirements.txt file

```bash
pip3 install -r requirements.txt #Linux/macOS
pip install -r requirements.txt #Windows
```

### Container

Build container image

```bash
docker build -t <YOUR_IMAGE_TARGET> .
```

Push Container to Docker Hub Repository

```bash
docker push <YOUR_IMAGE_TARGET>:<TAG>
```

### Kubernetes

Convert plain text to base64 to put in Kubernetes Secret

```bash
echo -n "YOUR_PLAIN_TEXT" | base64
```

Deploy all kind to your cluster!

```bash
kubectl apply -f ./k8s
```

Port-Forward to check on local

```bash
kubectl port-forward pods/<POD_NAME> <YOUR_PORT>:<TARGET_PORT>
kubectl port-forward deployment/<DEPLOYMENT_NAME> <YOUR_PORT>:<TARGET_PORT>
kubectl port-forward service/<SERVICE_NAME> <YOUR_PORT>:<TARGET_PORT>
```
