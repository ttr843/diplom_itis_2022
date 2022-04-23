import pickle

import numpy as np
import pandas as pd
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import confusion_matrix
from sklearn.model_selection import train_test_split

import util

df = pd.read_csv("C:/projects/diplom/diplom_itis_2022/ml/resources/prepared_data.csv")
x_basic = df.drop(columns=["result", "companyName", 'id'])
y = df["result"]

# Разделение данных на тестовые и обучающие наборы
x_train, x_test, y_train, y_test = train_test_split(x_basic, y, test_size=.33, random_state=1)
logistic_regression = LogisticRegression()

# Обучение базовой модели логистической регрессии с помощью обучающего набора
logistic_regression.fit(x_train, y_train)

pickle.dump(logistic_regression, open("resources/output/logistic_regression/model.sav", 'wb'))

print("intercept ")
print(logistic_regression.intercept_)
print("coefficients ")
print(logistic_regression.coef_)

# Прогнозирование вывода тестовых случаев с использованием алгоритма, созданного выше
y_predict = logistic_regression.predict(x_test)

util.print_result_score(y_test, y_predict)

cnf_matrix = confusion_matrix(y_test, y_predict)
np.set_printoptions(precision=2)

# Строим ненормализованную матрицу ошибок
util.plot_confusion_matrix(cnf_matrix, classes=["result"],
                           path_save_file="resources/output/logistic_regression/Confusion_matrix_Logistic_Regression"
                                          ".png",
                           title='Confusion matrix - Logistic Regression')
