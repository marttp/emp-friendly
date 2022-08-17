from sanic import Sanic
from sanic.response import text
from aredis_om import Migrator, get_redis_connection
from sanic.log import logger
from config import notification_sent_topic
from redis.client import PubSub
import asyncio

app = Sanic("NotificationMicroservice")


@app.before_server_start
async def setup_db(app: Sanic):
    logger.debug('Starting Sanic')
    app.ctx.redis = get_redis_connection()
    await Migrator().run()


@app.after_server_start
async def initial_after(app: Sanic):
    pubsub = app.ctx.redis.pubsub()
    await pubsub.subscribe(notification_sent_topic)
    task = asyncio.create_task(subscribe_topic(pubsub))
    await app.add_task(task)


async def subscribe_topic(pubsub: PubSub):
    async for message in pubsub.listen():
        logger.info(message)


@app.get('/health')
async def health(request):
    return text("OK!")


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8000, access_log=True, workers=1)
