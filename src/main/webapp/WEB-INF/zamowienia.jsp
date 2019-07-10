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
    <h2><a href="welcome">Strona glowna</a> </h2>
    <br/>
        <h3>Moje zamowienia</h3>
    <table>
        <b>
            <tr>
                <td>Nazwa produktu</td>
                <td>StatusZamowienia</td>
            </tr>
        </b>
        <c:forEach items="${zamowienia}" var="zamowienie">
        <tr>
            <td>${zamowienie.produkt.nazwaProduktu}</td>
            <td>${zamowienie.statusZamowienia}</td>
        </tr>
        </c:forEach>
        </c:if>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
