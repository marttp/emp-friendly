from redis_om import (Field, JsonModel)
from pydantic import BaseModel


class RestaurantModel(BaseModel):
    id: str
    name: str


class Restaurant(JsonModel):
    restaurant_id: str = Field(index=True)
    name: str = Field(index=True)
