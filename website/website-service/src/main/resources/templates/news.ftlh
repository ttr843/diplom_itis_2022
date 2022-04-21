<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Новости</title>
</head>
<body>
<a href="/website/admin/create-news" methods="get">
    <button type="button">Создать новость</button>
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
<#if newsList??>
    <table>
        <caption><h3>Новости</h3></caption>
        <tr>
            <th>Заголовок</th>
            <th>Текст</th>
            <th>Создана</th>
            <th>Удалена</th>
            <th>Действия</th>
        </tr>
        <#list newsList as news>
            <tr>
                <td><b>${news.getTitle()}</b></td>
                <td><b>${news.getText()}</b></td>
                <td><b>${news.getCreatedAt()}</b></td>
                <#if news.isDeleted()>
                    <td><b>Удалена</b></td>
                <#else>
                    <td><b>Не удалена</b></td>
                </#if>
                <#if news.isDeleted()>
                    <td><b>Нет действий</b></td>
                <#else>
                    <td>
                        <form action="/website/admin/news/delete/${news.getId()}" method="post" id="delete"></form>
                        <a onclick="document.getElementById('delete').submit();">
                            <button type="button">
                                <b>Удалить</b>
                            </button>
                        </a>
                    </td>
                </#if>
            </tr>
        </#list>
    </table>
</#if>
</body>
</html>