import uvicorn
import asyncio
import logging
from fastapi import FastAPI
from aredis_om import get_redis_connection
from config import invoice_create_topic
from redis.client import PubSub

logging.basicConfig(format='%(asctime)s - %(levelname)s - %(message)s',
                    level=logging.INFO)
log = logging.getLogger(__name__)

app = FastAPI()


@app.on_event("startup")
async def startup_event():
    client = get_redis_connection()
    pubsub = client.pubsub()
    await pubsub.subscribe(invoice_create_topic)
    asyncio.create_task(subscribe_topic(pubsub))


async def subscribe_topic(pubsub: PubSub):
    async for message in pubsub.listen():
        log.info(f'Message: {message}')


if __name__ == '__main__':
    uvicorn.run(app, port=8000, host='0.0.0.0')
