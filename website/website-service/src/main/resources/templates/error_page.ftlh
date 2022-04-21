<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ошибка</title>
</head>
<body>
<#if error??>
    <h3><b>Ошибка: ${error}</b></h3>
</#if>
<h4>
    Вы можете обратится в техническую поддержку по адресу : <a href="mailto:website.score.analysis.tech@gmail.com">website.score.analysis.tech@gmail.com</a>
</h4>
<a href="/website/main" methods="get">
    <button type="button">Вернуться на главную</button>
</a>
<p></p>
<a href="/website/logout" methods="get">
    <button type="button">Выйти</button>
</a>
</body>
</html>