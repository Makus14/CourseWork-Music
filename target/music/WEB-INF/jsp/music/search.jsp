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

<!-- Begin | Wrapper  -->
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
            <jsp:param name="page" value="search"/>
        </jsp:include>

        <div class="banner bg-song"></div>

        <!-- Begin | Main Container  -->
        <div class="main-container" id="appRoute">

            <%-- ALERTS!!!--%>
            <jsp:include page="../fragment/alerts.jsp"/>

            <div class="heading">
                <div class="d-flex flex-wrap align-items-end">
                    <div class="flex-grow-1">
                        <h4><fmt:message key="label.searchResult" bundle="${loc}"/></h4>
                    </div>
                </div>
                <hr>
            </div>

            <%--ARTISTS--%>
            <div class="section">
                <!-- Begin | Search Result List -->
                <div class="mb-3">
                    <c:if test="${not empty requestScope.artistsList}">
                        <!-- Begin | Search Result List Header -->
                        <div class="d-flex">
                            <span class="text-uppercase mr-auto font-weight-bold text-dark">
                                <fmt:message key="label.artists" bundle="${loc}"/>
                            </span>
                            <a href="${pageContext.servletContext.contextPath}/controller?command=artists">
                                <fmt:message key="label.viewAll" bundle="${loc}"/>
                            </a>
                        </div>
                        <!-- End | Search Result List Header -->
                        <hr>

                        <!-- Begin | Result List -->
                        <div class="row">
                            <c:forEach items="${requestScope.artistsList}" var="artist">
                                <div class="col-xl-2 col-md-4 col-6">
                                    <div class="custom-card mb-3">
                                        <a href="${pageContext.servletContext.contextPath}/controller?command=artists&artistName=${artist.artistName}"
                                           class="text-dark">
                                            <img src="${pageContext.request.contextPath}/resource/img/artists/${artist.imagePath}"
                                                 alt="" class="card-img--radius-md">
                                            <p class="text-truncate mt-2"><c:out value="${artist.artistName}"/></p>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <!-- End | Result List -->
                    </c:if>
                </div>
                <!-- End | Search Result List -->

                <c:if test="${not empty requestScope.songs}">
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
                        <jsp:param name="page" value="search"/>
                    </jsp:include>
                </c:if>

                <!-- Begin | Search Result List -->
                <div>
                    <c:if test="${not empty requestScope.albums}">
                        <!-- Begin | Search Result List Header -->
                        <div class="d-flex">
                            <span class="text-uppercase mr-auto font-weight-bold text-dark">
                                <fmt:message key="label.albums" bundle="${loc}"/>
                            </span>
                            <a href="${pageContext.servletContext.contextPath}/controller?command=home"><fmt:message key="label.viewAll" bundle="${loc}"/></a>
                        </div>
                        <!-- End | Search Result List Header -->
                        <hr>
                        <!-- Begin | Result List -->
                        <div class="row">
                            <div class="col-xl-4 col-md-6 col-12">
                                <div class="custom-card mb-3">
                                    <c:forEach items="${requestScope.albums}" var="album">
                                        <div class="custom-card">
                                            <div class="custom-card--img">
                                                <a href="${pageContext.servletContext.contextPath}/controller?command=artists&artistName=${param.artistName}&albumName=${album.albumName}">
                                                    <img src="resource/img/album1.jpg"
                                                         class="card-img--radius-md" alt="album1.jpg">
                                                    <span class="bg-blur"><c:out value="${album.albumName}"/></span>
                                                </a>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <!-- End | Result List -->
                    </c:if>
                </div>
                <!-- End | Search Result List -->
            </div>
        </div>
        <!-- End | Main Container -->
        <footer id="footer" class="bg-img"></footer>

    </main>
</div>

<div class="backdrop header-backdrop"></div>
<div class="backdrop sidebar-backdrop"></div>

</body>
</html>