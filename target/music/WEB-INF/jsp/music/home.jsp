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

    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/jquery.perfect-scrollbar/0.6.7/js/min/perfect-scrollbar.jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
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

    <!-- Begin | Page Wrapper  -->
    <main id="pageWrapper">

        <jsp:include page="../fragment/header.jsp">
            <jsp:param name="page" value="home"/>
        </jsp:include>

        <div class="banner bg-song"></div>

        <div class="main-container" id="appRoute">

            <%-- ALERTS!!!--%>
            <jsp:include page="../fragment/alerts.jsp"/>

            <div class="heading">
                <div class="d-flex flex-wrap align-items-end">
                    <div class="flex-grow-1">
                        <h4><fmt:message key="label.songs" bundle="${loc}"/></h4>
                        <p><fmt:message key="label.listenEnjoy" bundle="${loc}"/></p>
                    </div>
                </div>
                <hr>
            </div>

            <jsp:include page="../fragment/songs.jsp">
                <jsp:param name="page" value="home"/>
            </jsp:include>

            <div class="section for-genres">
                <div class="heading">
                    <div class="d-flex flex-wrap align-items-end">
                        <div class="flex-grow-1">
                            <h4><fmt:message key="label.genres" bundle="${loc}"/></h4>
                            <p><fmt:message key="label.selectGenre" bundle="${loc}"/></p>
                        </div>
                        <a href="${pageContext.servletContext.contextPath}/controller?command=genres"
                           class="btn btn-sm btn-pill btn-air btn-primary">
                            <fmt:message key="label.viewAll" bundle="${loc}"/>
                        </a>
                    </div>
                    <hr>
                </div>

                <div class="carousel-item-6 arrow-pos-1">

                    <c:forEach items="${sessionScope.genres}" var="genre">
                        <div class="custom-card">
                            <div class="custom-card--img">
                                <a href="${pageContext.servletContext.contextPath}/controller?command=genres&genre=${genre.key}">
                                    <img src="${pageContext.request.contextPath}/resource/img/genres/Music-ear-Image.jpg"
                                         alt="${genre.value}"
                                         class="card-img--radius-md">
                                    <span class="bg-blur"><c:out value="${genre.value}"/></span>
                                </a>
                            </div>
                        </div>
                    </c:forEach>
                </div>

            </div>


        </div>

        <footer id="footer" class="bg-img">
        </footer>

    </main>
</div>
<!-- End | Wrapper -->

<!-- Back Drop -->
<div class="backdrop header-backdrop"></div>
<div class="backdrop sidebar-backdrop"></div>


</body>
</html>
