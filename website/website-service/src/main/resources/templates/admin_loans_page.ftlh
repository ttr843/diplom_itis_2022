<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Все Заявки</title>
</head>
<body>
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
<#if loans??>
    <table>
        <caption><h3>Заявки</h3></caption>
        <tr>
            <th>Номер заявки</th>
            <th>Название компании</th>
            <th>ИНН компании</th>
            <th>ОГРН компании</th>
            <th>Размер компании</th>
            <th>Вид дейтельности, отрасль</th>
            <th>Кредитный лимит, RUB</th>
            <th>Сумма незавершенных исков в роли ответчика, RUB</th>
            <th>Сумма исполнительных производств, RUB</th>
            <th>Среднесписочная численность работников</th>
            <th>Уставный капитал, RUB</th>
            <th>Выручка, RUB</th>
            <th>Чистая прибыль (убыток), RUB</th>
            <th>Создана</th>
            <th>Статус обработки</th>
            <th>Кем создана</th>
        </tr>
        <#list loans as loan>
            <tr>
                <#assign mlStatusId = loan.getMlStatus().getId()>
                <#if mlStatusId == 1>
                <b style="background-color:white;">
                    <#elseif mlStatusId == 2>
                    <b style="background-color:blue;">
                        <#elseif mlStatusId == 3>
                        <b style="background-color:red;">
                            <#elseif mlStatusId == 4>
                            <b style="background-color:yellow;">
                                <#elseif mlStatusId == 5>
                                <b style="background-color:green;">
                                    </#if>
                                    <td>${loan.getId()}</td>
                                    <td>${loan.getCompanyName()}</td>
                                    <td>${loan.getCompanyInn()}</td>
                                    <td>${loan.getCompanyOgrn()}</td>
                                    <td>${loan.getCompanySizeType().getName()}</td>
                                    <td>${loan.getIndustry().getName()}</td>
                                    <td>${loan.getCreditLimit()}</td>
                                    <td>${loan.getAmountOfLawsuits()}</td>
                                    <td>${loan.getAmountOfProceedings()}</td>
                                    <td>${loan.getAmountOfWorkers()}</td>
                                    <td>${loan.getCapital()}</td>
                                    <td>${loan.getRevenue()}</td>
                                    <td>${loan.getNetProfit()}</td>
                                    <td>${loan.getCreatedAt()}</td>
                                    <td>${loan.getMlStatus().getName()}</td>
                                    <td>${loan.getClient().getFirstName()} + ${loan.getClient().getMiddleName()}
                                        + ${loan.getClient().getLastName()}</td>
                                </b>
            </tr>
        </#list>
    </table>
</#if>
</body>
</html>