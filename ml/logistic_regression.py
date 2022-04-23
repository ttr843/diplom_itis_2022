import itertools
import pickle

import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score, f1_score, precision_score, recall_score
from sklearn.metrics import confusion_matrix
from sklearn.model_selection import train_test_split


def plot_confusion_matrix(cm, classes,
                          path_save_file,
                          normalize=False,
                          title='Confusion matrix',
                          cmap=plt.cm.Blues):
    plt.figure()
    if normalize:
        cm = cm.astype('float') / cm.sum(axis=1)[:, np.newaxis]
        print("Нормализованная матрица ошибок")
    else:
        print('Матрица ошибок')

    print(cm)

    plt.imshow(cm, interpolation='nearest', cmap=cmap)
    plt.title(title)
    plt.colorbar()
    tick_marks = np.arange(len(classes))
    plt.xticks(tick_marks, classes, rotation=45)
    plt.yticks(tick_marks, classes)

    fmt = '.2f' if normalize else 'd'
    thresh = cm.max() / 2.
    for i, j in itertools.product(range(cm.shape[0]), range(cm.shape[1])):
        plt.text(j, i, format(cm[i, j], fmt),
                 horizontalalignment="center",
                 color="white" if cm[i, j] > thresh else "black")

    plt.tight_layout()
    plt.ylabel('Реальность')
    plt.xlabel('Прогноз')
    plt.show()
    plt.savefig(path_save_file, bbox_inches='tight')


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

print("")
a1 = accuracy_score(y_test, y_predict)
f1 = f1_score(y_test, y_predict, average="macro")
p1 = precision_score(y_test, y_predict, average="macro")
r1 = recall_score(y_test, y_predict, average="macro")
print("accuracy score : ", a1)
print("f1 score : ", f1)
print("precision score : ", p1)
print("recall score : ", r1)

cnf_matrix = confusion_matrix(y_test, y_predict)
np.set_printoptions(precision=2)

# Строим ненормализованную матрицу ошибок
plot_confusion_matrix(cnf_matrix, classes=["result"],
                      path_save_file="resources/output/logistic_regression/Confusion_matrix_Logistic_Regression"
                                     ".png",
                      title='Confusion matrix - Logistic Regression')
