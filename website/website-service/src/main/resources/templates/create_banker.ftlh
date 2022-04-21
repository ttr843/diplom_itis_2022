<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Добавить сотрудника банка</title>
</head>
<body>
<a href="/website/admin/users" methods="get">
    <button type="button">Вернуться к пользователям</button>
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
<h3><b>Создать сотрудника банка</b></h3>
<p></p>
<form action="/website/admin/create-banker" method="POST">
    <div>
        <input type="text" placeholder="Почта" name="email">
    </div>
    <p></p>
    <div>
        <input type="text" placeholder="Имя" name="firstName">
    </div>
    <p></p>
    <div>
        <input type="text" placeholder="Отчество" name="middleName">
    </div>
    <p></p>
    <div>
        <input type="text" placeholder="Фамилия" name="lastName">
    </div>
    <p></p>
    <div>
        <input type="text" placeholder="Номер телефона" name="phoneNumber">
    </div>
    <p></p>
    <div>
        <input type="number" placeholder="Возраст" name="age">
    </div>
    <p></p>
    <div>
        <input type="text" placeholder="Страна" name="country">
    </div>
    <p></p>
    <div>
        <input type="text" placeholder="Город" name="city">
    </div>
    <p></p>
    <div>
        <input type="text" placeholder="Улица" name="street">
    </div>
    <p></p>
    <div>
        <input type="number" placeholder="Номер дома" name="house">
    </div>
    <p></p>
    <div>
        <input type="password" placeholder="Пароль" name="password">
    </div>
    <p></p>
    <div>
        <select name="bankId">
            <#list banks as bank>
                <#if bank??>
                    <option selected value="${bank.getId()}"> ${bank.getName()} </option>
                </#if>
            </#list>
        </select>
    </div>
    <p></p>
    <div>
        <input type="text" placeholder="Должность" name="position">
    </div>
    <p></p>
    <div>
        <button type="submit">Создать</button>
    </div>
</form>
</body>
</html>