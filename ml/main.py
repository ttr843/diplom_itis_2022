from flask import Flask, request
import json
import random
from predict_app import predict

app = Flask(__name__)


@app.route('/predict')
def search_something():
    if 'mock' in request.args.keys():
        if request.args['mock']:
            return random.choice([True, False])
        else:
            predict()

    else:
        return False;


if __name__ == "__main__":
    app.run(host="localhost", debug=True, port=8080)
