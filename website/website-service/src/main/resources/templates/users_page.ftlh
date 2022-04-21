<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Пользователи</title>
</head>
<body>
<a href="/website/admin/create-banker" methods="get">
    <button type="button">Создать сотрудника банка</button>
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

<#if users??>
    <table>
        <caption><h3>Пользователи</h3></caption>
        <tr>
            <th>Имя</th>
            <th>Отчество</th>
            <th>Фамилия</th>
            <th>Номер телефона</th>
            <th>Почта</th>
            <th>Роль</th>
            <th>Название банка</th>
            <th>Должность</th>
            <th>Активна</th>
            <th>Действия</th>
        </tr>
        <#list users as user>
            <tr>
                <td>${user.getFirstName()}</td>
                <td>${user.getMiddleName()}</td>
                <td>${user.getLastName()}</td>
                <td>${user.getPhoneNumber()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getRole().getName()}</td>
                <#if user.getBank()??>
                    <td>${user.getBank().getName()}</td>
                <#else>
                    <td></td>
                </#if>
                <#if user.getPosition()??>
                    <td>${user.getPosition()}</td>
                <#else>
                    <td></td>
                </#if>
                <#if user.isBlocked()>
                    <td>Нет</td>
                <#else>
                    <td>Да</td>
                </#if>
                <#if user.isBlocked()>
                    <td>
                        <form action="/website/admin/users/unblock/${user.getId()}" method="post" id="unblock"></form>
                        <a onclick="document.getElementById('unblock').submit();">
                            <button type="button">
                                <b>
                                    Разблокировать
                                </b>
                            </button>
                        </a>
                    </td>
                <#else>
                    <td>
                        <form action="/website/admin/users/block/${user.getId()}" method="post" id="block"></form>
                        <a onclick="document.getElementById('block').submit();">
                            <button type="button">
                                <b>
                                    Заблокировать
                                </b>
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