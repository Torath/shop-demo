<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Sklep</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Witaj, ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Wyloguj</a></h2>
        <h2><a href="zamowienia">Moje zamowienia</a></h2>

        <br/>
        <table>
            <b>
                <tr>
                    <td>Nazwa produktu</td>
                    <td>cena</td>
                </tr>
            </b>
            <c:forEach items="${produkty}" var="produkt">
            <tr>
                <td>${produkt.nazwaProduktu}</td>
                <td>${produkt.cena}</td>
                <td><a href="zamowProdukt/${produkt.id}">Zamow</a></td>
            </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
