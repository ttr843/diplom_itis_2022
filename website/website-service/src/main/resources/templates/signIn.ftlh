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
 Войти
<form action="/website/auth/signIn" method="post">
    <div>
        <input type="text" placeholder="email" name="email">
    </div>
    <div>
        <input type="password" placeholder="password" name="password">
    </div>
    <div>
        <button type="submit">Войти</button>
    </div>
</form>
</body>
</html>