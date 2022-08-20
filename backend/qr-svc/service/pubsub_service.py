import json

from sanic.log import logger
from redis.client import PubSub

import config
from config import payment_error_topic, payment_completed_topic, create_new_restaurant_topic
from service.qr_code_service import update_qr_status, create_qr_code, create_restaurant_qr_code


async def handle_message(message: dict):
    logger.debug(f'Payment subscribed message: {message}')
    data = message['data']
    data = json.loads(data)
    channel = message['channel'].decode()
    if channel == payment_completed_topic:
        qr_id = data['qr_id']
        logger.debug('Payment Success!')
        # update_qr_status(qr_id, config.STATUS.get('success'))
    elif channel == payment_error_topic:
        qr_id = data['qr_id']
        logger.debug('Payment Error!')
        # update_qr_status(qr_id, config.STATUS.get('error'))
    elif channel == create_new_restaurant_topic:
        logger.debug("Create new QR for restaurant")
        create_restaurant_qr_code(data)


async def subscribe_topic(pubsub: PubSub):
    async for message in pubsub.listen():
        if message['type'] == 'message':
            await handle_message(message)
