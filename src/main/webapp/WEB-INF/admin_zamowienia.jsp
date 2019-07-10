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
    <h2>Panel Administratora, ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Wyloguj</a></h2>
    <h2><a href="welcome">Strona glowna</a></h2>
    <br/>
        <h3>Zamowienia</h3>
    <table>
        <b>
            <tr>
                <td>Nazwa produktu</td>
                <td>Login zamawiajacego</td>
                <td>Adres wysylki</td>
                <td>Status zamowienia</td>
                <td>Edytuj status</td>
            </tr>
        </b>
        <c:forEach items="${zamowienia}" var="zamowienie">
        <tr>
            <td>${zamowienie.produkt.nazwaProduktu}</td>
            <td>${zamowienie.user.username}</td>
            <td>${zamowienie.adresWysylki}</td>
            <td>NOWE</td>
            <td>
            <form:form method="POST" modelAttribute="zamowienie" class="form-signin">
            <form:select path="kategoria">
                <form:option value="-" label="--Wybierz kategorie"/>
                <form:options items="${enumValues}" />
            </form:select>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Zapisz</button>
            </form:form>
            </td>
            <td> </td>
        </tr>
        </c:forEach>
        </c:if>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
