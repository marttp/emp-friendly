import os

payment_completed_topic = os.getenv('PAYMENT_COMPLETED_TOPIC_NAME', 'payment-completed-topic')
payment_error_topic = os.getenv('PAYMENT_ERROR_TOPIC_NAME', 'payment-error-topic')
create_new_restaurant_topic = os.getenv('CREATE_NEW_RESTAURANT_TOPIC_NAME', 'create-new-restaurant-topic')
payment_url = os.getenv('PAYMENT_URL_FOR_QR', '/payments/{id}')
payment_restaurant_url = os.getenv('PAYMENT_RESTAURANT_URL_FOR_QR', '/payments/restaurants/{id}')

STATUS = {
    'active': 'active',
    'inactive': 'inactive',
    'processing': 'processing',
    'success': 'success',
    'expired': 'expired',
    'cancel': 'canceled',
    'error': 'error'
}
