<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Welcome Page</title>
</head>
<body>
<h3><b>Регистрация</b></h3>
<p></p>
<form action="/website/auth/signUp" method="POST">
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
        <button type="submit">Регистрация</button>
    </div>
</form>
<a href="/website/auth/signIn" methods="get">
    Есть аккаунт? Войти!
</a>
</body>
</html>