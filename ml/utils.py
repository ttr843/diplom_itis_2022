import itertools

import matplotlib.pyplot as plt
import numpy as np
from sklearn.metrics import accuracy_score, f1_score, precision_score, recall_score


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


def print_metrics(y_test, y_predict):
    a1 = accuracy_score(y_test, y_predict)
    f1 = f1_score(y_test, y_predict, average="macro")
    p1 = precision_score(y_test, y_predict, average="macro")
    r1 = recall_score(y_test, y_predict, average="macro")
    print("accuracy score : ", a1)
    print("f1 score : ", f1)
    print("precision score : ", p1)
    print("recall score : ", r1)
