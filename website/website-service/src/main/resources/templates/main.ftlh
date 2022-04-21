<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Главная страница</title>
</head>
<body>
<#if currentUser??>
    <#assign role = currentUser.getRole()>
    <#if role??>
        <#assign code = role.getCode()>
        <#if code == "ADMIN">
            <a href="/website/admin" methods="get">
                <button type="button">Перейти в админ панель</button>
            </a>
        <#else>
            <a href="/website/loan" methods="get">
                <button type="button">Перейти к заявкам</button>
            </a>
        </#if>
    </#if>
</#if>
<p></p>
<a href="/website/main" methods="get">
    <button type="button">Главная</button>
</a>
<p></p>
<a href="/website/logout" methods="get">
    <button type="button">Выйти</button>
</a>
<h3><b>Новости</b></h3>
<#if newsList??>
    <#list newsList as news>
        <h4><b>Заголовок: ${news.getTitle()}</b></h4>
        <h5>Создана : ${news.getCreatedAt()}</h5>
        <h5>Текст: ${news.getText()}</h5>
    </#list>
</#if>
</body>
</html>