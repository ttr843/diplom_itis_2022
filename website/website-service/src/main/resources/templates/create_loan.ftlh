<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Добавить Заявку</title>
</head>
<body>
<a href="/website/loan" methods="get">
    <button type="button">Вернуться к заявкам</button>
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
<h3><b>Создать Заявку</b></h3>
<p></p>
<form action="/website/loan/create" method="POST">
    <div>
        <input type="text" placeholder="Название компании" name="companyName">
    </div>
    <p></p>
    <div>
        <input type="text" placeholder="ИНН компании" name="companyInn">
    </div>
    <p></p>
    <div>
        <input type="text" placeholder="ОГРН компании" name="companyOgrn">
    </div>
    <p></p>
    <div>
        <select name="industryId">
            <#list industries as industry>
                <#if industry??>
                    <option selected value="${industry.getId()}"> ${industry.getName()} </option>
                </#if>
            </#list>
        </select>
    </div>
    <p></p>
    <div>
        <input type="number" placeholder="Кредитный лимит, RUB" name="creditLimit">
    </div>
    <p></p>
    <div>
        <input type="number" placeholder="Сумма незавершенных исков в роли ответчика, RUB" name="amountOfLawsuits">
    </div>
    <p></p>
    <div>
        <input type="number" placeholder="Сумма исполнительных производств, RUB" name="amountOfProceedings">
    </div>
    <p></p>
    <div>
        <input type="number" placeholder="Среднесписочная численность работников" name="amountOfWorkers">
    </div>
    <p></p>
    <div>
    <input type="number" placeholder="Уставный капитал, RUB" name="capital">
    </div>
    <p></p>
    <div>
        <input type="number" placeholder="Выручка, RUB" name="revenue">
    </div>
    <p></p>
    <div>
        <input type="number" placeholder="Чистая прибыль (убыток), RUB" name="netProfit">
    </div>
    <p></p>
    <div>
        <select name="companySizeTypeId">
            <#list companySizeTypes as companySizeType>
                <#if companySizeType??>
                    <option selected value="${companySizeType.getId()}"> ${companySizeType.getName()} </option>
                </#if>
            </#list>
        </select>
    </div>
    <p></p>
    <div>
        <button type="submit">Создать</button>
    </div>
</form>
</body>
</html>