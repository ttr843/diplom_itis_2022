<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Создать новость</title>
</head>
<body>
<a href="/website/admin/news" methods="get">
    <button type="button">Вернуться к новостям</button>
</a>
<p></p>
<a href="/website/admin" methods="get">
    <button type="button">Перейти в админ панель</button>
</a>
<p></p>
<a href="/website/main" methods="get">
    <button type="button">Главная</button>
</a>
<p></p>
<a href="/website/logout" methods="get">
    <button type="button">Выйти</button>
</a>
<p></p>
<h3><b>Добавить новость</b></h3>
<p></p>
<form action="/website/admin/create-news" method="POST">
    <div>
        <input type="text" placeholder="Заголовок" name="title">
    </div>
    <p></p>
    <div>
        <input type="text" placeholder="текст" name="text">
    </div>
    <p></p>
    <div>
        <button type="submit">Добавить новость</button>
    </div>
</form>
</body>
</html>