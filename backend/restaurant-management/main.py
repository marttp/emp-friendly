import logging
import uvicorn
from fastapi import FastAPI
from redis_om import Migrator
from models import RestaurantModel
from restaurants import restaurants, create, delete

logging.basicConfig(format='%(asctime)s - %(levelname)s - %(message)s',
                    level=logging.INFO)
log = logging.getLogger(__name__)

app = FastAPI()
Migrator().run()


@app.on_event("startup")
async def startup_event():
    restaurant_list = [
        RestaurantModel(id='a3cbc4f1-3636-4db9-bb42-36c49ba655b9', name='SHOP-1'),
        RestaurantModel(id='d72d77fb-e96d-4d9a-964d-f2bf605c7e0b', name='SHOP-2'),
        RestaurantModel(id='c820da4c-d8de-4229-848b-33e5f183a22c', name='SHOP-3'),
        RestaurantModel(id='f084e35a-f745-4c86-b0d5-aae81bd632a9', name='SHOP-4'),
        RestaurantModel(id='d31710fc-ba84-4ef9-ae2e-3c8e38e1c84c', name='SHOP-5')
    ]
    for r in restaurant_list:
        await delete(r.id)
        await create(r)


app.include_router(restaurants, prefix='/restaurants', tags=['restaurants'])

if __name__ == '__main__':
    uvicorn.run(app, port=8000, host='0.0.0.0')
