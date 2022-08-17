from aredis_om import get_redis_connection
from sanic import Blueprint
from sanic import response, HTTPResponse, Request
from models import RatingHistory
from functools import reduce

bp = Blueprint("Rating", url_prefix="/ratings")
client = get_redis_connection()

# @bp.get('/')
# async def hello(request) -> HTTPResponse:
#     body = {
#         'user_id': 'test_user',
#         'target_id': 'test_target',
#         'type': 'Restaurant',
#         'rate': 5,
#         'created_date': datetime.utcnow()
#     }
#     await client.publish(received_rating_topic, json.dumps(body, default=str))
#     return response.json({'status': 'Success'})

# {"user_id": "test_user", "target_id": "test_target", "type": "Restaurant", "rate": 3, "created_date": "2022-08-17 02:17:30.176224"}


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
