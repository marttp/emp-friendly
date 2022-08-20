from sanic import Sanic, Request, HTTPResponse, response
from aredis_om import get_redis_connection
from redis_om import Migrator
from sanic.log import logger

import config
from config import payment_error_topic, payment_completed_topic, create_new_restaurant_topic
import asyncio
from service.pubsub_service import subscribe_topic
from service import qr_code_service, gen_qr_service

app = Sanic("QRCodeMicroservice")
Migrator().run()


@app.before_server_start
async def setup_db(app: Sanic):
    logger.debug('Starting Sanic')
    app.ctx.redis = get_redis_connection()


@app.after_server_start
async def initial_after(app: Sanic):
    pubsub = app.ctx.redis.pubsub()
    await pubsub.subscribe(create_new_restaurant_topic)
    task = asyncio.create_task(subscribe_topic(pubsub))
    await app.add_task(task)
    # for topic in [payment_completed_topic, payment_error_topic, create_new_restaurant_topic]:
    #     await pubsub.subscribe(topic)
    #     task = asyncio.create_task(subscribe_topic(pubsub))
    #     await app.add_task(task)


@app.get('/<qr_id:str>')
async def get_qr_code(request: Request, qr_id: str) -> HTTPResponse:
    result = qr_code_service.get_qr_code(qr_id)
    if result.status != config.STATUS.get('processing'):
        raise ValueError("Status not processing")
    return response.json({'base64': gen_qr_service.gen_qr_payment(result.payment_id)})


@app.get('/restaurants/<restaurant_id:str>')
async def get_qr_code(request: Request, restaurant_id: str) -> HTTPResponse:
    result = qr_code_service.get_restaurant_qr_code(restaurant_id)
    return response.json({'base64': gen_qr_service.gen_qr_for_restaurant(result.restaurant_id)})


@app.post('/')
async def create_qr_code(request: Request) -> HTTPResponse:
    body = request.json
    result = qr_code_service.create_qr_code(body)
    return response.json({'result': result.pk}, status=201)


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8000, access_log=True, workers=1)
