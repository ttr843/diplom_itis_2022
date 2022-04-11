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
 регистрация
<form action="/website/auth/signUp" method="POST">
    <div>
        <input type="text" placeholder="email" name="email">
    </div>
    <div>
        <input type="text" placeholder="firstName" name="firstName">
    </div>
    <div>
        <input type="text" placeholder="middleName" name="middleName">
    </div>
    <div>
        <input type="text" placeholder="lastName" name="lastName">
    </div>
    <div>
        <input type="text" placeholder="phoneNumber" name="phoneNumber">
    </div>
    <div>
        <input type="number" placeholder="age" name="age">
    </div>
    <div>
        <input type="text" placeholder="country" name="country">
    </div>
    <div>
        <input type="text" placeholder="city" name="city">
    </div>
    <div>
        <input type="text" placeholder="street" name="street">
    </div>
    <div>
        <input type="number" placeholder="house" name="house">
    </div>
    <div>
        <input type="password" placeholder="password" name="password">
    </div>
    <div>
        <button type="submit">Регистрация</button>
    </div>
</form>
</body>
</html>