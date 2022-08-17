FROM python:3.10.6-slim-buster

WORKDIR /sanic

RUN apt-get update

RUN apt-get install -y build-essential

COPY requirements.txt .

RUN pip install --no-cache-dir --upgrade -r requirements.txt

COPY . .

EXPOSE 8000

CMD ["python", "server.py"]
