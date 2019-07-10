<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>Admin panel</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Panel administratora | <a href="zamowienia">Zamowienia</a> | <a onclick="document.forms['logoutForm'].submit()">Wyloguj</a></h2>

        <a href="dodajProdukt">[Dodaj produkt]</a>
        <br/>
        <table>
            <b>
        <tr>
            <td>Nazwa produktu</td>
            <td>cena</td>
            <td>ilosc w magazynie</td>
        </tr>
            </b>
        <c:forEach items="${produkty}" var="produkt">
            <tr>
                <td>${produkt.nazwaProduktu}</td>
                <td>${produkt.cena}</td>
                <td>${produkt.iloscWMagazynie}</td>
                <td><a href="edytujProdukt/${produkt.id}">Edytuj</a></td>
            </tr>
        </c:forEach>
        <table/>



    </c:if>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>