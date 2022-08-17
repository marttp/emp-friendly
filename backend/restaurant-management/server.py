from sanic import Sanic

app = Sanic("RestaurantManagementMicroservice")

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8000, access_log=True, workers=1)
