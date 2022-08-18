FROM python:3.10.6-slim-buster

WORKDIR /code

RUN apt-get update

RUN apt-get install -y build-essential

COPY ./requirements.txt /code/requirements.txt

RUN pip install --no-cache-dir --upgrade -r /code/requirements.txt

COPY . /code/

CMD ["python", "main.py"]