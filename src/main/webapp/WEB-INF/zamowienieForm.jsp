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
    <form:form method="POST" modelAttribute="editItemForm" class="form-signin">
        <h2 class="form-signin-heading">Chcesz zamowic produkt: ${produkt.nazwaProduktu}</h2>
        Podaj adres wysylki:
        <spring:bind path="adresWysylki">
            <form:input type="text" path="adresWysylki" class="form-control" placeholder="Adres"
                        autofocus="true"></form:input>
        </spring:bind>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Zamow</button><a href="welcome">Anuluj</a>
    </form:form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>