from datetime import datetime

import config
from models import QRCode, RestaurantQRCode
from redis_om.model import NotFoundError


# Get
def get_qr_code(qr_id: str):
    try:
        qr_code_model = QRCode.get(qr_id)
    except NotFoundError:
        raise NotFoundError(f'Not found data for {qr_id}')
    return qr_code_model


def get_restaurant_qr_code(restaurant_id: str):
    qr_code_list = RestaurantQRCode.find(RestaurantQRCode.restaurant_id == restaurant_id).all()
    if len(qr_code_list) == 0:
        raise NotFoundError(f'Not found data for restaurant_id: {restaurant_id}')
    res = qr_code_list[0]
    return res


# Create
def create_qr_code(data: dict):
    data['status'] = config.STATUS.get('processing')
    data['created_date'] = datetime.utcnow()
    qr_code_model = QRCode(**data)
    qr_code_model.save()
    return qr_code_model


def create_restaurant_qr_code(data: dict):
    data['status'] = config.STATUS.get('active')
    res_qr_model = RestaurantQRCode(**data)
    res_qr_model.save()
    return res_qr_model


# Update
def update_qr_status(qr_id: str, status: str) -> QRCode:
    try:
        qr_code_model = QRCode.get(qr_id)
    except NotFoundError:
        raise NotFoundError(f'Not found data for {qr_id}')
    qr_code_model.status = status
    qr_code_model.save()
    return qr_code_model
