<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Заявки</title>
</head>
<body>
<a href="/website/main" methods="get">
    <button type="button">Главная</button>
</a>
<p></p>
<#if currentUser??>
    <#assign role = currentUser.getRole()>
    <#if role??>
        <#assign code = role.getCode()>
        <#if code == "USER">
            <a href="/website/loan/create" methods="get">
                <button type="button">Создать заявку</button>
            </a>
            <p></p>
        <#else>
        </#if>
    </#if>
</#if>
<a href="/website/logout" methods="get">
    <button type="button">Выйти</button>
</a>
<p></p>
<#if currentUser??>
    <#assign role = currentUser.getRole()>
    <#if role??>
        <#assign code = role.getCode()>
        <#if code == "USER">
            <#if loans??>
                <table>
                    <caption><h3>Мои заявки</h3></caption>
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
                        <th>Одобрена банком</th>
                        <th>Время одобрения</th>
                        <th>Кем Одобрена</th>
                        <th>Должность сотрудника</th>
                        <th>Банк</th>
                    </tr>
                    <#list loans as loan>
                        <tr>
                            <#assign mlStatusId = loan.getMlStatusId()>
                            <td>${loan.getId()}</td>
                            <td>${loan.getCompanyName()}</td>
                            <td>${loan.getCompanyInn()}</td>
                            <td>${loan.getCompanyOgrn()}</td>
                            <td>${loan.getCompanySizeTypeName()}</td>
                            <td>${loan.getIndustryName()}</td>
                            <td>${loan.getCreditLimit()}</td>
                            <td>${loan.getAmountOfLawsuits()}</td>
                            <td>${loan.getAmountOfProceedings()}</td>
                            <td>${loan.getAmountOfWorkers()}</td>
                            <td>${loan.getCapital()}</td>
                            <td>${loan.getRevenue()}</td>
                            <td>${loan.getNetProfit()}</td>
                            <td>${loan.getCreatedAt()}</td>
                            <#if mlStatusId == 1>
                                <td style="color:black">${loan.getMlStatusName()}</td>
                            <#elseif mlStatusId == 2>
                                <td style="color:blue">${loan.getMlStatusName()}
                            <#elseif mlStatusId == 3>
                                <td style="color:red">${loan.getMlStatusName()}</td>
                            <#elseif mlStatusId == 4>
                                <td style="color:orange">${loan.getMlStatusName()}</td>
                            <#elseif mlStatusId == 5>
                                <td style="color:green">${loan.getMlStatusName()}</td>
                            </#if>
                            <#assign approvedStatus = loan.getApprovedByBank()>
                            <#if approvedStatus>
                                <td style="color:green">Да</td>
                            <#else>
                                <td style="color:blue">В ожидании</td>
                            </#if>
                            <#if loan.getApprovedByBankAt()??>
                                <td>${loan.getApprovedByBankAt()}</td>
                                <td>${loan.getBankerFullName()}</td>
                                <td>${loan.getBankerPosition()}</td>
                                <td>${loan.getBankName()}</td>
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
        <#else>
            <#if loans??>
                <table>
                    <caption><h3>Готовые к утверждению заявки</h3></caption>
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
                        <th>Действие</th>
                    </tr>
                    <#list loans as loan>
                        <tr>
                            <#assign mlStatusId = loan.getMlStatusId()>
                            <td>${loan.getId()}</td>
                            <td>${loan.getCompanyName()}</td>
                            <td>${loan.getCompanyInn()}</td>
                            <td>${loan.getCompanyOgrn()}</td>
                            <td>${loan.getCompanySizeTypeName()}</td>
                            <td>${loan.getIndustryName()}</td>
                            <td>${loan.getCreditLimit()}</td>
                            <td>${loan.getAmountOfLawsuits()}</td>
                            <td>${loan.getAmountOfProceedings()}</td>
                            <td>${loan.getAmountOfWorkers()}</td>
                            <td>${loan.getCapital()}</td>
                            <td>${loan.getRevenue()}</td>
                            <td>${loan.getNetProfit()}</td>
                            <td>${loan.getCreatedAt()}</td>
                            <#if mlStatusId == 1>
                                <td style="color:black">${loan.getMlStatusName()}</td>
                            <#elseif mlStatusId == 2>
                                <td style="color:blue">${loan.getMlStatusName()}
                            <#elseif mlStatusId == 3>
                                <td style="color:red">${loan.getMlStatusName()}</td>
                            <#elseif mlStatusId == 4>
                                <td style="color:orange">${loan.getMlStatusName()}</td>
                            <#elseif mlStatusId == 5>
                                <td style="color:green">${loan.getMlStatusName()}</td>
                            </#if>
                            <td>${loan.getCreatedByFullName()}</td>
                            <#assign approvedStatus = loan.getApprovedByBank()>
                            <#if approvedStatus>
                                <td style="color:green">Да</td>
                            <#else>
                                <td style="color:blue">В ожидании</td>
                            </#if>
                            <#if loan.getApprovedByBankAt()??>
                                <td>${loan.getApprovedByBankAt()}</td>
                                <td>${loan.getBankerFullName()}</td>
                                <td>${loan.getBankerPosition()}</td>
                                <td>${loan.getBankName()}</td>
                            <#else>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </#if>
                            <#if approvedStatus>
                                <td><b>Нет действий</b></td>
                            <#else>
                                <td>
                                    <form action="/website/loan/approve/${loan.getGuid()}" method="post"
                                          id="approve"></form>
                                    <a onclick="document.getElementById('approve').submit();">
                                        <button type="button">
                                            <b>Согласовать</b>
                                        </button>
                                    </a>
                                </td>
                            </#if>
                        </tr>
                    </#list>
                </table>
            </#if>
        </#if>
    </#if>
</#if>
</body>
</html>