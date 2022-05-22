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
        <caption><h3>Все заявки</h3></caption>
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
            <th>Одобрена банком</th>
            <th>Время одобрения</th>
            <th>Кем Одобрена</th>
            <th>Должность сотрудника</th>
            <th>Банк</th>
            <th>Действия</th>
        </tr>
        <#list loans as loan>
            <tr>
                <#assign mlStatusId = loan.getMlStatus().getId()>
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
                <#if mlStatusId == 1>
                    <td style="color:black">${loan.getMlStatus().getName()}</td>
                <#elseif mlStatusId == 2>
                    <td style="color:blue">${loan.getMlStatus().getName()}
                <#elseif mlStatusId == 3>
                    <td style="color:red">${loan.getMlStatus().getName()}</td>
                <#elseif mlStatusId == 4>
                    <td style="color:orange">${loan.getMlStatus().getName()}</td>
                <#elseif mlStatusId == 5>
                    <td style="color:green">${loan.getMlStatus().getName()}</td>
                </#if>
                <td>${loan.getClient().getFirstName() + " "} ${loan.getClient().getMiddleName() + " "} ${loan.getClient().getLastName()}</td>
                <#assign approvedStatus = loan.isApprovedByBank()>
                <#if approvedStatus>
                    <td style="color:green">Да</td>
                <#else>
                    <td style="color:blue">В ожидании</td>
                </#if>
                <#if loan.getApprovedByBankAt()??>
                    <td>${loan.getApprovedByBankAt()}</td>
                    <td>${loan.getBanker().getFirstName() + " "} ${loan.getBanker().getMiddleName() + " "} ${loan.getBanker().getLastName() + " "} </td>
                    <td>${loan.getBanker().getPosition()}</td>
                    <td>${loan.getBanker().getBank().getName()}</td>
                <#else>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </#if>
            </tr>
        </#list>
    </table>
</#if>
</body>
</html>