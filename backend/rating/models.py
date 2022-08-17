import datetime

from pydantic import PositiveInt
from redis_om import (Field, JsonModel)


class RatingHistory(JsonModel):
    user_id: str = Field(index=True)
    target_id: str = Field(index=True)
    type: str = Field(index=True)
    rate: PositiveInt = Field(index=True)
    created_date: datetime.datetime
