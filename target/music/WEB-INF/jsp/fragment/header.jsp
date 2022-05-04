<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="resourcebundle.pagecontent" var="loc"/>

<!-- Begin | Header [[ Find at scss/framework/base/header/header.scss ]] -->
<header id="header" class="bg-primary">

    <div class="d-flex align-items-center">


        <form action="${pageContext.servletContext.contextPath}/controller?command=search" method="post"
              id="searchForm">
            <button type="button" class="btn ion-ios-search"></button>
            <input type="text" placeholder="<fmt:message key="label.search" bundle="${loc}"/>" id="searchInput"
                   name="searchInput"
                   class="form-control"
                   autocomplete="off" maxlength="40">
        </form>

        <!-- Begin | Header Options -->
        <ul class="header-options d-flex align-items-center">

            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userMenu">
                <jsp:include page="../fragment/nav-language.jsp">
                    <jsp:param name="page" value="login"/>
                </jsp:include>
            </div>


            <li class="dropdown fade-in">
                <a href="javascript:void(0);" class="d-flex align-items-center py-2" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="pl-2"><fmt:message key="title.language" bundle="${loc}"/></span>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userMenu">
                    <a class="dropdown-item"

                       href="${pageContext.servletContext.contextPath}/controller?command=changeLanguage&lang=en_EN&page=${param.page}">
                        <span><fmt:message bundle="${loc}" key="label.languageEn"/></span>
                    </a>

                    <a class="dropdown-item"
                       href="${pageContext.servletContext.contextPath}/controller?command=changeLanguage&lang=ru_RU&page=${param.page}">
                        <span><fmt:message bundle="${loc}" key="label.languageRu"/></span>
                    </a>
                </div>
            </li>

            <li class="dropdown fade-in">
                <a href="javascript:void(0);" class="d-flex align-items-center py-2" role="button" id="userMenu"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <span class="pl-2">
                        <c:if test="${sessionScope.user.role.accountRole == 'CLIENT'}">
                            <fmt:message key="label.hello" bundle="${loc}"/> ${sessionScope.user.firstName}!
                        </c:if>
                        <c:if test="${sessionScope.user.role.accountRole == 'ADMIN'}">
                            <fmt:message key="label.helloAdmin" bundle="${loc}"/>
                        </c:if>
                    </span>
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userMenu">
                    <c:if test="${sessionScope.user.role.accountRole == 'CLIENT'}">
                        <a class="dropdown-item"
                           href="${pageContext.servletContext.contextPath}/controller?command=profilePage">
                            <i class="ion-md-contact"></i>
                            <span><fmt:message key="label.profile" bundle="${loc}"/></span>
                        </a>

                        <div class="dropdown-divider"></div>
                    </c:if>


                    <div class="px-4 py-2">
                        <a href="${pageContext.servletContext.contextPath}/controller?command=logout"
                           class="btn btn-sm btn-air btn-pill btn-danger">
                            <fmt:message key="label.logout" bundle="${loc}"/>
                        </a>
                    </div>
                </div>
            </li>
        </ul>
        <!-- End | Header Options -->
    </div>

</header>

<div class="banner"></div>







    <%--<div class="alert alert-success alert-dismissible">
        <button type="button" class="close" data-dismiss="alert">&times;</button>
        <strong>Success!</strong> Indicates a successful or positive action.
    </div>
--%><%--<div class="alert alert-success alert-dismissible">
    <a href="" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong>Success!</strong> Indicates a successful or positive action.
</div>--%>




<%--<div class="alert alert-info">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong>Info!</strong> Indicates a neutral informative change or action.
</div>--%>

<%--<div class="alert alert-secondary" role="alert">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong>Info!</strong> Indicates a neutral informative change or action.
    &lt;%&ndash;This is a secondary alert with <a href="#" class="alert-link">an example link</a>. Give it a click if you like.&ndash;%&gt;
</div>--%>

<%--<div class="alert alert-danger fade in alert-dismissible show">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true" style="font-size:20px">&times;</span>
    </button>    <strong>Danger!</strong> This alert box indicates a dangerous or potentially negative action.
</div>--%>


<%--<div class="alert alert-secondary">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong>Secondary!</strong> Indicates a slightly less important action.
</div>--%>



<!-- End | Header -->
