<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="resourcebundle.pagecontent" var="loc"/>

<html>
<head>
    <title>Music Time <fmt:message key="title.login" bundle="${loc}"/></title>
    <link rel="shortcut icon" href='<c:url value="/resource/img/wallpaper-music.png"/>' type="image/png">
    <link type="text/css" rel="stylesheet" href='<c:url value="/resource/css/loginpage.css"/>'>
</head>

<body>



<div id="wrapper" data-scrollable="true">

    <main id="pageWrapper">

        <div class="main-container under-banner-content" id="appRoute">

<div id="head">
    <nav>
        <ul>
            <jsp:include page="../fragment/nav-language.jsp">
                <jsp:param name="page" value="login"/>
            </jsp:include>
        </ul>
    </nav>
</div>


<div class="server-answer">
    <c:if test="${not empty requestScope.parametersInfo}">
        <p class="infos">${requestScope.parametersInfo}</p>
    </c:if>
</div>

<form class="form" method="post" action="${pageContext.servletContext.contextPath}/controller?command=login">

    <h1><fmt:message key="label.signIn" bundle="${loc}"/></h1>
    <div class="input-form">
        <input type="text" name="login" placeholder=<fmt:message key="label.login" bundle="${loc}"/> required maxlength="20">
    </div>
    <div class="input-form">
        <input type="password" name="password" placeholder=<fmt:message key="label.password" bundle="${loc}"/> required maxlength="30">
    </div>

    <c:if test="${not empty requestScope.errorAuthorisation}">
        <div class="errorInfo">
            <c:out value="${requestScope.errorAuthorisation}"/>
        </div>
    </c:if>


    <div class="input-form">
        <input type="submit" value=<fmt:message key="label.signIn" bundle="${loc}"/>>
    </div>

    <p><fmt:message key="label.noAccount" bundle="${loc}"/></p>
    <a href="${pageContext.servletContext.contextPath}/controller?command=signup"><fmt:message key="label.signUp" bundle="${loc}"/></a>

</form>

        </div>
    </main>
</div>

</body>
</html>
