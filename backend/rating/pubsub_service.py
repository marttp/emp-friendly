import json

from sanic.log import logger
from models import RatingHistory
from config import received_rating_topic
from redis.client import PubSub


async def handle_message(message: dict):
    data = message['data']
    data = json.loads(data)
    channel = message['channel'].decode()
    if channel == received_rating_topic:
        new_rating_history = RatingHistory(**data)
        logger.info(new_rating_history)
        new_rating_history.save()
        logger.info(f'Save {new_rating_history} success')


async def subscribe_topic(pubsub: PubSub):
    async for message in pubsub.listen():
        if message['type'] == 'message':
            await handle_message(message)
