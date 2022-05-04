<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="resourcebundle.pagecontent" var="loc"/>

<li>

    <a>
        <fmt:message bundle="${loc}" key="title.language"/>
    </a>

    <ul>
        <li>
            <a href="${pageContext.servletContext.contextPath}/controller?command=changeLanguage&lang=en_EN&page=${param.page}">
                <fmt:message bundle="${loc}" key="label.languageEn"/>
            </a>
        </li>
        <li>
            <a href="${pageContext.servletContext.contextPath}/controller?command=changeLanguage&lang=ru_RU&page=${param.page}">
                <fmt:message bundle="${loc}" key="label.languageRu"/>
            </a>
        </li>
    </ul>

</li>
