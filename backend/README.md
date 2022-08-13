# Technical Informations

## Version

- Java 17
- Python 3.10.4
- Spring Boot 2.6.6
- Sanic 22.6 (venv)
- Container: Jib (Spring) + own Dockerfile (Sanic)

## Related Commands

### Gradle

Build container image

```bash
gradle jib --image=<YOUR_IMAGE_TARGET>
```

### Python

Build container image

```bash
docker build -t <YOUR_IMAGE_TARGET> .
```
