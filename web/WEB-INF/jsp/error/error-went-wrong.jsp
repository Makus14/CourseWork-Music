<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="resourcebundle.pagecontent" var="loc"/>

<html>
<head>
    <title>Error</title>
    <link type="text/css" rel="stylesheet" href='<c:url value="/resource/css/error.css"/>'>
</head>

<body class="went-wrong">
<div class="message">
    <a onclick="history.back(); return false;">
        <fmt:message bundle="${loc}" key="answer.errorWentWrong"/>
    </a>
</div>
</body>
</html>
