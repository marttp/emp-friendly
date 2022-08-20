import os

payment_completed_topic = os.getenv('PAYMENT_COMPLETED_TOPIC_NAME', 'payment-completed-topic')
payment_error_topic = os.getenv('PAYMENT_ERROR_TOPIC_NAME', 'payment-error-topic')
payment_url = os.getenv('PAYMENT_URL_FOR_QR', '/payments/{id}')

STATUS = {
    'processing': 'processing',
    'success': 'success',
    'expired': 'expired',
    'cancel': 'canceled',
    'error': 'error'
}
