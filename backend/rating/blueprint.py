from aredis_om import get_redis_connection
from sanic import Blueprint
from sanic import response, HTTPResponse, Request
from models import RatingHistory
from functools import reduce
from config import received_rating_topic
import json

bp = Blueprint("Rating", url_prefix="/ratings")
client = get_redis_connection()


@bp.get('/<target_id:str>')
async def get_rating_by_id(request: Request, target_id: str) -> HTTPResponse:
    rating_list = RatingHistory.find(RatingHistory.target_id == target_id).all()
    size = len(rating_list)
    if size == 0:
        return response.json({'rating': 0.0})
    rating_num_list = [r.rate for r in rating_list]
    sum = reduce(lambda a, b: a + b, rating_num_list)
    result = sum / size
    return response.json({'rating': result})


@bp.post('/')
async def add_rating(request: Request) -> HTTPResponse:
    body = request.json
    print(body)
    await client.publish(received_rating_topic, json.dumps(body, default=str))
    return response.empty(status=201)
