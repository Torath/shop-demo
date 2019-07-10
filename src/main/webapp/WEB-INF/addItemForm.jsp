<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <title>DodajProdukt</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <form:form method="POST" modelAttribute="produktForm" class="form-signin">
        <h2 class="form-signin-heading">Dodaj produkt</h2>
        Nazwa produktu:
        <spring:bind path="nazwaProduktu">
                <form:input type="text" path="nazwaProduktu" class="form-control" placeholder="Nazwa Produktu"
                            autofocus="true"></form:input>
        </spring:bind>
        <br/>Cena:
        <spring:bind path="cena">
                <form:input type="number" path="cena" class="form-control" placeholder="9,99"></form:input>
        </spring:bind>
        <br/>Ilosc w magazynie:
        <spring:bind path="iloscWMagazynie">
            <form:input type="number" path="iloscWMagazynie" class="form-control" placeholder="1"></form:input>
        </spring:bind>
        <br/>Kategoria:
        <form:select path="kategoria">
            <form:option value="-" label="--Wybierz kategorie"/>
            <form:options items="${enumValues}" />
        </form:select>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Dodaj</button><a href="welcome.jsp">Anuluj</a>
    </form:form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>