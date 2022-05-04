<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="resourcebundle.pagecontent" var="loc"/>

<html>
<head>
    <title><fmt:message key="title.signUp" bundle="${loc}"/></title>
    <link rel="shortcut icon" href='<c:url value="/resource/img/purple-img.png"/>' type="image/png">
    <link type="text/css" rel="stylesheet" href='<c:url value="/resource/css/loginpage.css"/>'>
</head>

<body>

<div id="head">
    <nav>
        <ul>
            <jsp:include page="../fragment/nav-language.jsp">
                <jsp:param name="page" value="signup"/>
            </jsp:include>
        </ul>
    </nav>
</div>

<form class="form" method="post" action="${pageContext.servletContext.contextPath}/controller?command=register">

    <h1><fmt:message key="title.signUp" bundle="${loc}"/></h1>
    <div class="input-form">
        <input type="text" name="firstName" placeholder="<fmt:message key="label.firstName" bundle="${loc}"/>" required
               maxlength="20" value="${param.get('firstName')}">
    </div>
    <c:if test="${not empty requestScope.invalidFirstName}">
        <div class="errorInfo"><c:out value="${requestScope.invalidFirstName}"/></div>
    </c:if>
    <div class="input-form">
        <input type="text" name="lastName" placeholder="<fmt:message key="label.lastName" bundle="${loc}"/>" required
               maxlength="20" value="${param.get('lastName')}">
    </div>
    <c:if test="${not empty requestScope.invalidLastName}">
        <div class="errorInfo"><c:out value="${requestScope.invalidLastName}"/></div>
    </c:if>
    <div class="input-form">
        <input type="email" name="email" placeholder="Email" required maxlength="45" value="${param.get('email')}">
    </div>
    <c:if test="${not empty requestScope.invalidEmail}">
        <div class="errorInfo"><c:out value="${requestScope.invalidEmail}"/></div>
    </c:if>
    <div class="input-form">
        <input type="text" name="login" placeholder="<fmt:message key="label.login" bundle="${loc}"/>" required
               maxlength="20" value="${param.get('login')}">
    </div>
    <c:if test="${not empty requestScope.invalidLogin}">
        <div class="errorInfo"><c:out value="${requestScope.invalidLogin}"/></div>
    </c:if>
    <div class="input-form">
        <input type="password" name="password" placeholder="<fmt:message key="label.password" bundle="${loc}"/>"
               required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,30}" value="${param.get('password')}">
    </div>
    <c:if test="${not empty requestScope.invalidPassword}">
        <div class="errorInfo"><c:out value="${requestScope.invalidPassword}"/></div>
    </c:if>
    <div class="input-form">
        <input type="password" name="confirmPassword"
               placeholder="<fmt:message key="label.confirmPassword" bundle="${loc}"/>" required
               pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,30}" value="${param.get('confirmPassword')}">
    </div>
    <c:if test="${not empty requestScope.invalidConfirmPassword}">
        <div class="errorInfo"><c:out value="${requestScope.invalidConfirmPassword}"/></div>
    </c:if>
    <c:if test="${not empty requestScope.invalidPasswordsMatch}">
        <div class="errorInfo"><c:out value="${requestScope.invalidPasswordsMatch}"/></div>
    </c:if>
    <div class="secure">
        * <fmt:message key="label.securePassword" bundle="${loc}"/>
    </div>

    <div class="input-form">
        <input type="submit" value="<fmt:message key="label.signUp" bundle="${loc}"/>">
    </div>

    <p><fmt:message key="label.haveAccount" bundle="${loc}"/></p>
    <a href="${pageContext.servletContext.contextPath}/controller?command=login"><fmt:message key="label.signIn"
                                                                                              bundle="${loc}"/></a>

</form>


</body>

</html>
