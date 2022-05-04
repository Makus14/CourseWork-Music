<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="resourcebundle.pagecontent" var="loc"/>

<html>
<head>

    <title>Music Time</title>

    <link href='<c:url value="/resource/img/purple-img.png"/>' rel="icon"/>

    <!-- Styles -->
    <link href='<c:url value="/assets/css/vendors.bundle.css"/>' rel="stylesheet" type="text/css"/>
    <link href='<c:url value="/assets/css/styles.bundle.css"/>' rel="stylesheet" type="text/css"/>

    <!-- Google fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:100,300,400,700" rel="stylesheet">

    <link rel="stylesheet" href="https://bootstraptema.ru/plugins/2015/audio-touch/audio-touch.css"/>

    <script src="assets/js/vendors.bundle.js"></script>
    <script src="assets/js/scripts.bundle.js"></script>

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>


<body>

<jsp:include page="../fragment/loading.jsp"/>

<!-- Begin | Wrapper -->
<div id="wrapper" data-scrollable="true">

    <c:if test="${sessionScope.user.role.accountRole == 'CLIENT'}">
        <jsp:include page="../fragment/left-aside-client.jsp"/>
    </c:if>
    <c:if test="${sessionScope.user.role.accountRole == 'ADMIN'}">
        <jsp:include page="../fragment/left-aside-admin.jsp"/>
    </c:if>


    <!-- Begin | Page Wrapper -->
    <main id="pageWrapper">

        <jsp:include page="../fragment/header.jsp">
            <jsp:param name="page" value="profilePage"/>
        </jsp:include>

        <div class="main-container under-banner-content" id="appRoute">

            <%-- ALERTS!!!--%>
            <jsp:include page="../fragment/alerts.jsp"/>

            <div class="row section">
                <div class="col-xl-10 mx-auto">

                    <div class="col-xl-8 col-md-7">
                        <div class="card h-auto">
                            <div class="card-body">
                                <form action="${pageContext.servletContext.contextPath}/controller?command=profile"
                                      method="post" class="row">
                                    <div class="col-xl-6 form-group">
                                        <label for="firstName" class="form-label">
                                            <fmt:message key="label.firstName" bundle="${loc}"/>
                                        </label>
                                        <input type="text" id="firstName" name="firstName" class="form-control"
                                               value="${user.firstName}" required maxlength="20">
                                    </div>
                                    <c:if test="${not empty requestScope.invalidFirstName}">
                                        <div class="errorInfo"><c:out value="${requestScope.invalidFirstName}"/></div>
                                    </c:if>

                                    <div class="col-xl-6 form-group">
                                        <label for="lastName" class="form-label">
                                            <fmt:message key="label.lastName" bundle="${loc}"/>
                                        </label>
                                        <input type="text" id="lastName" name="lastName" class="form-control" required
                                               value="${user.lastName}" maxlength="20">
                                    </div>
                                    <c:if test="${not empty requestScope.invalidLastName}">
                                        <div class="errorInfo"><c:out value="${requestScope.invalidLastName}"/></div>
                                    </c:if>

                                    <div class="col-12 form-group">
                                        <label for="email" class="form-label">Email</label>
                                        <input type="email" id="email" name="email" class="form-control" required
                                               value="${user.email}" maxlength="65">
                                    </div>
                                    <c:if test="${not empty requestScope.invalidEmail}">
                                        <div class="errorInfo"><c:out value="${requestScope.invalidEmail}"/></div>
                                    </c:if>

                                    <div class="col-xl-6 form-group">
                                        <label for="login" class="form-label">
                                            <fmt:message key="label.login" bundle="${loc}"/>
                                        </label>
                                        <input type="text" id="login" name="login" class="form-control" required
                                               value="${user.login}">
                                    </div>
                                    <c:if test="${not empty requestScope.invalidLogin}">
                                        <div class="errorInfo"><c:out value="${requestScope.invalidLogin}"/></div>
                                    </c:if>

                                    <div class="col-xl-6 form-group">
                                        <label for="password" class="form-label">
                                            <fmt:message key="label.password" bundle="${loc}"/>
                                        </label>
                                        <input id="password" name="password" type="password" class="form-control"
                                               value="" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,30}">
                                    </div>
                                    <c:if test="${not empty requestScope.invalidPassword}">
                                        <div class="errorInfo"><c:out value="${requestScope.invalidPassword}"/></div>
                                    </c:if>
                                    <div class="col-12">
                                        <button type="submit" class="btn btn-primary btn-air">
                                            <fmt:message key="label.saveChanges" bundle="${loc}"/>
                                        </button>
                                    </div>

                                </form>
                            </div>
                        </div>

                        <%--подписка--%>
                        <c:if test="${!sessionScope.user.subscription}">
                            <div class="plan-info-card text-center px-sm-5 py-sm-4 p-3">
                                <h6><fmt:message key="label.notSubscribed" bundle="${loc}"/></h6>
                                <p><fmt:message key="label.subscriptionOptions" bundle="${loc}"/></p>
                                <a href="${pageContext.servletContext.contextPath}/controller?command=subscription" class="btn btn-pill btn-air btn-success"><fmt:message key="label.subscribe" bundle="${loc}"/></a>
                            </div>
                        </c:if>

                        <c:if test="${sessionScope.user.subscription}">
                            <div class="plan-info-card text-center px-sm-5 py-sm-4 p-3">
                                <h6><fmt:message key="label.subscribed" bundle="${loc}"/></h6>
                                <%--<p><fmt:message key="label.subscriptionOptions" bundle="${loc}"/></p>--%>
                                <a href="${pageContext.servletContext.contextPath}/controller?command=subscription" class="btn btn-pill btn-air btn-success"><fmt:message key="label.unsubscribe" bundle="${loc}"/></a>
                            </div>
                        </c:if>


                    </div>

                </div>
            </div>
        </div>
        <!-- End | Main Container -->

        <footer id="footer" class="bg-img"></footer>

    </main>
    <!-- End | Page Wrapper -->

</div>
<!-- End | Wrapper -->

<div class="backdrop header-backdrop"></div>
<div class="backdrop sidebar-backdrop"></div>

<script>
    if (window.history.replaceState) {
        window.history.replaceState(null, null, window.location.href);
    }
</script>

</body>
</html>
