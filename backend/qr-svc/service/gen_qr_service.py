import qrcode
import base64
from PIL import Image
from io import BytesIO
from config import payment_url


def gen_qr(payment_id: str) -> str:
    input_data = payment_url.replace('{id}', payment_id)
    data = gen_plain_qr_code(input_data)
    return data.decode(encoding="utf-8")


def gen_plain_qr_code(input_data: str) -> bytes:
    # Creating an instance of qrcode
    qr = qrcode.QRCode(
        version=1,
        box_size=10,
        border=3)
    qr.add_data(input_data)
    qr.make(fit=True)
    img = qr.make_image(fill='black', back_color='white')

    buffer = BytesIO()
    img.save(buffer, format="PNG")
    img_str = base64.b64encode(buffer.getvalue())
    return img_str


def base64data_to_image(data: str):
    buff = BytesIO(base64.b64decode(data))
    image = Image.open(buff)
    return image
