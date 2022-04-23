import numpy as np
import pandas as pd
from sklearn.metrics import confusion_matrix
from sklearn.model_selection import train_test_split
from sklearn.tree import DecisionTreeClassifier

import util

df = pd.read_csv("C:/projects/diplom/diplom_itis_2022/ml/resources/prepared_data.csv")
x_basic = df.drop(columns=["result", "companyName"])
y = df["result"]

# Разделение данных на тестовые и обучающие наборы
x_train, x_test, y_train, y_test = train_test_split(x_basic, y, test_size=.33, random_state=1)

decision_tree_classifier = DecisionTreeClassifier()
decision_tree_classifier.max_depth = 100

# Обучение базовой модели дерева решений с помощью обучающего набора
decision_tree_classifier.fit(x_train, y_train)

# Прогнозирование вывода тестовых случаев с помощью алгоритма, созданного выше
y_pre = decision_tree_classifier.predict(x_test)

util.print_result_score(y_test, y_pre)

cnf_matrix = confusion_matrix(y_test, y_pre)
np.set_printoptions(precision=2)

# Строим ненормализованную матрицу ошибок
util.plot_confusion_matrix(cnf_matrix, classes=["result"],
                           path_save_file="resources/output/decision_tree/"
                                          "Confusion_matrix_Decision_Tree_classifier.png",
                           title='Confusion matrix - Decision Tree classifier')
