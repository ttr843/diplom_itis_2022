import itertools
import pickle

import graphviz
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from sklearn import tree
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
x_basic = df.drop(columns=["result", "companyName"])
y = df["result"]

# Разделение данных на тестовые и обучающие наборы
x_train, x_test, y_train, y_test = train_test_split(x_basic, y, test_size=.33, random_state=1)

decision_tree_classifier = tree.DecisionTreeClassifier()
decision_tree_classifier.max_depth = 100

# Обучение базовой модели дерева решений с помощью обучающего набора
decision_tree_classifier.fit(x_train, y_train)

pickle.dump(decision_tree_classifier, open("resources/output/decision_tree/model.sav", 'wb'))

# Прогнозирование вывода тестовых случаев с помощью алгоритма, созданного выше
y_predict = decision_tree_classifier.predict(x_test)

print("---START---")
print(y_test)
print("---HALF---")
print(y_predict)
print("---END---")
a1 = accuracy_score(y_test, y_predict)
f1 = f1_score(y_test, y_predict, average="macro")
p1 = precision_score(y_test, y_predict, average="macro")
r1 = recall_score(y_test, y_predict, average="macro")
print("accuracy score : ", a1)
print("f1 score : ", f1)
print("precision score : ", p1)
print("recall score : ", r1)

cnf_matrix = confusion_matrix(y_test, y_predict)
tn, fp, fn, tp = cnf_matrix.ravel()
print(tn, fp, fn, tp)

np.set_printoptions(precision=2)


# Строим ненормализованную матрицу ошибок
plot_confusion_matrix(cnf_matrix, classes=["result"],
                       path_save_file=
                       "resources/output/decision_tree/Confusion_matrix_Decision_Tree_classifier.png",
                       title='Confusion matrix - Decision Tree classifier')
