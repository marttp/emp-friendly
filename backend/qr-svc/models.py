import datetime

from redis_om import (Field, JsonModel)


class QRCode(JsonModel):
    payment_id: str = Field(index=True)
    status: str = Field(index=True)
    created_date: datetime.datetime


class RestaurantQRCode(JsonModel):
    restaurant_id: str = Field(index=True)
    status: str = Field(index=True)
