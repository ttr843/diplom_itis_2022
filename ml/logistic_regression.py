import pickle

import pandas as pd
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import confusion_matrix
from sklearn.model_selection import train_test_split

import utils

data_path = "C:/projects/diplom/diplom_itis_2022/ml/resources/prepared_data.csv"
output_path = "resources/output/logistic_regression/model.sav";
matrix_output_path = "resources/output/logistic_regression/Confusion_matrix_Logistic_Regression.png"

df = pd.read_csv(data_path)
x_basic = df.drop(columns=["result", "companyName", 'id'])
y = df["result"]

# Разделение данных на тестовые и обучающие наборы
x_train, x_test, y_train, y_test = train_test_split(x_basic, y, test_size=.33, random_state=1)
logistic_regression = LogisticRegression()

# Обучение базовой модели логистической регрессии с помощью обучающего набора
logistic_regression.fit(x_train, y_train)

pickle.dump(logistic_regression, open(output_path, 'wb'))

# Прогнозирование вывода тестовых случаев с использованием алгоритма, созданного выше
y_predict = logistic_regression.predict(x_test)
utils.print_metrics(y_test, y_predict)
cnf_matrix = confusion_matrix(y_test, y_predict)
tn, fp, fn, tp = cnf_matrix.ravel()
# Строим ненормализованную матрицу ошибок
utils.plot_confusion_matrix(cnf_matrix, classes=["negative"],
                            path_save_file=matrix_output_path,
                            title='Матрица ошибок логистической регрессии')
print(tn, fp, fn, tp)
