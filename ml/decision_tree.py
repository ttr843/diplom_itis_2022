import pickle

import pandas as pd
from sklearn import tree
from sklearn.metrics import confusion_matrix
from sklearn.model_selection import train_test_split

import utils

data_path = "C:/projects/diplom/diplom_itis_2022/ml/resources/prepared_data.csv"
output_path = "resources/output/decision_tree/model.sav";
matrix_output_path = "resources/output/decision_tree/Confusion_matrix_Decision_Tree_classifier.png"

df = pd.read_csv(data_path)
x_basic = df.drop(columns=["result", "companyName", 'id'])
y = df["result"]

# Разделение данных на тестовые и обучающие наборы
x_train, x_test, y_train, y_test = train_test_split(x_basic, y, test_size=.33, random_state=1)
decision_tree_classifier = tree.DecisionTreeClassifier()
decision_tree_classifier.max_depth = 100
# Обучение базовой модели дерева решений с помощью обучающего набора
decision_tree_classifier.fit(x_train, y_train)
pickle.dump(decision_tree_classifier, open(output_path, 'wb'))
# Прогнозирование вывода тестовых случаев с помощью алгоритма, созданного выше
y_predict = decision_tree_classifier.predict(x_test)
utils.print_metrics(y_test, y_predict)
cnf_matrix = confusion_matrix(y_test, y_predict)
tn, fp, fn, tp = cnf_matrix.ravel()
print(tn, fp, fn, tp)

# Строим ненормализованную матрицу ошибок
utils.plot_confusion_matrix(cnf_matrix, classes=["result"],
                            path_save_file=matrix_output_path,
                            title='Матрица ошибок Дерева решений')
