from sanic import Sanic
from aredis_om import get_redis_connection
from redis_om import Migrator
from sanic.log import logger
from config import received_rating_topic
import asyncio
from pubsub_service import subscribe_topic
from blueprint import bp

application = Sanic('RatingMicroservice')
Migrator().run()


@application.before_server_start
async def setup_db(app: Sanic):
    logger.debug('Starting Sanic')
    app.ctx.redis = get_redis_connection()


@application.after_server_start
async def initial_after(app: Sanic):
    pubsub = app.ctx.redis.pubsub()
    await pubsub.subscribe(received_rating_topic)
    task = asyncio.create_task(subscribe_topic(pubsub))
    await app.add_task(task)


application.blueprint(bp)

if __name__ == '__main__':
    application.run(host='0.0.0.0', port=8000, access_log=True, workers=1)
