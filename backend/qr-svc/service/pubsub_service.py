import json

from sanic.log import logger
from redis.client import PubSub

import config
from config import payment_error_topic, payment_completed_topic
from service.qr_code_service import update_qr_status


async def handle_message(message: dict):
    logger.debug(f'Payment subscribed message: {message}')
    data = message['data']
    data = json.loads(data)
    channel = message['channel'].decode()
    qr_id = data['qr_id']
    if channel == payment_completed_topic:
        logger.debug('Payment Success!')
        update_qr_status(qr_id, config.STATUS.get('success'))
    elif channel == payment_error_topic:
        logger.debug('Payment Error!')
        update_qr_status(qr_id, config.STATUS.get('error'))


async def subscribe_topic(pubsub: PubSub):
    async for message in pubsub.listen():
        if message['type'] == 'message':
            await handle_message(message)
