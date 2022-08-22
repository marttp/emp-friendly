import json
from typing import List
from fastapi import APIRouter, HTTPException

import config
from models import Restaurant, RestaurantModel
from aredis_om import NotFoundError, get_redis_connection
import logging

log = logging.getLogger(__name__)
restaurants = APIRouter()


@restaurants.post('', response_model=Restaurant, status_code=201)
async def create(payload: RestaurantModel):
    log.info("Create Restaurant")
    restaurant = Restaurant(
        restaurant_id=payload.id,
        name=payload.name
    )
    restaurant.save()
    client = get_redis_connection()
    message_payload = {
        'restaurant_id': restaurant.restaurant_id
    }
    await client.publish(config.create_new_restaurant_topic, json.dumps(message_payload))
    return restaurant


@restaurants.get('', response_model=List[Restaurant])
async def read() -> List[Restaurant]:
    log.info("Read All Restaurant")
    return Restaurant.find().all()


@restaurants.delete('/{id}', response_model=None, status_code=204)
async def delete(id: str):
    log.info("Delete Restaurant")
    try:
        restaurant_list = Restaurant.find(Restaurant.restaurant_id == id).all()
        for r in restaurant_list:
            r.delete(r.pk)
    except NotFoundError:
        raise HTTPException(status_code=404, detail="Restaurant not found")
