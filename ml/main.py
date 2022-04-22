import json
import pickle

import numpy
import pandas as pd
from flask import Flask, request

app = Flask(__name__)


@app.route('/website/ml/predict')
def search_something():
    loaded_model = pickle.load(
        open("C:/projects/diplom/diplom_itis_2022/ml/resources/output/logistic_regression/model.sav", 'rb'))
    data = [[request.args['industryId'], request.args['creditLimit'], request.args['amountOfLawsuits'],
             request.args['amountOfProceedings'],
             request.args['companySizeTypeId'], request.args['amountOfWorkers'], request.args['capital'],
             request.args['revenue'], request.args['netProfit']]]
    df = pd.DataFrame(data, columns=['industryId', 'creditLimit', 'amountOfLawsuits', 'amountOfProceedings',
                                     'companySizeTypeId', 'amountOfWorkers', 'capital', 'revenue', 'netProfit'])
    result = loaded_model.predict(df)
    return json.dumps(int(result))


if __name__ == "__main__":
    app.run(host="localhost", debug=True, port=9108)
