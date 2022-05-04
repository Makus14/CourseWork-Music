<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<%@ taglib uri="customTag" prefix="ctg" %>
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

<div id="wrapper" data-scrollable="true">

    <c:if test="${sessionScope.user.role.accountRole == 'CLIENT'}">
        <jsp:include page="../fragment/left-aside-client.jsp"/>
    </c:if>
    <c:if test="${sessionScope.user.role.accountRole == 'ADMIN'}">
        <jsp:include page="../fragment/left-aside-admin.jsp"/>
    </c:if>

    <main id="pageWrapper">

        <jsp:include page="../fragment/header.jsp">
            <jsp:param name="page" value="playlistsPage"/>
        </jsp:include>

        <div class="banner bg-song"></div>

        <div class="main-container" id="appRoute">

            <%-- ALERTS!!!--%>
            <jsp:include page="../fragment/alerts.jsp"/>

            <c:if test="${not empty requestScope.songs}">
            <div class="section">
                <div class="heading">
                    <div class="d-flex flex-wrap align-items-end">
                        <div class="flex-grow-1">
                            <h4><fmt:message key="label.playlist" bundle="${loc}"/> <c:out
                                    value="${param.playlistName}"/>. <fmt:message key="label.songs"
                                                                                  bundle="${loc}"/></h4>
                            <p><fmt:message key="label.listenEnjoy" bundle="${loc}"/></p>
                        </div>
                        <hr>
                    </div>
                </div>

                <c:if test="${not empty requestScope.songs}">
                    <jsp:include page="../fragment/songs.jsp">
                        <jsp:param name="page" value="playlists"/>
                    </jsp:include>
                </c:if>
            </c:if>


                <div class="heading">
                    <div class="d-flex flex-wrap align-items-end">
                        <div class="flex-grow-1">
                            <h4><fmt:message key="label.playlists" bundle="${loc}"/></h4>
                        </div>
                    </div>
                    <hr>
                </div>


                <ctg:playlistListBody objects="${requestScope.playlistList}"/>

                <%--<div class="section row">
                    <c:forEach items="${requestScope.playlistList}" var="playlist">
                        <div class="col-xl-3 col-lg-4 col-sm-6 pb-4">
                            <div class="custom-card">
                                <div class="custom-card--img">
                                    <a href="${pageContext.servletContext.contextPath}/controller?command=playlists&playlistName=${playlist.playlistName}">
                                        <img src="${pageContext.request.contextPath}/resource/img/playlist.jpg"
                                             alt="playlist"
                                             class="card-img--radius-lg">
                                    </a>
                                </div>

                                <a href="${pageContext.servletContext.contextPath}/controller?command=playlists&playlistName=${playlist.playlistName}"
                                   class="custom-card--link mt-2">
                                    <h6 class="mb-0"><c:out value="${playlist.playlistName}"/></h6>
                                </a>

                                <c:if test="${playlist.playlistName != 'favorite'}">
                                    <a href="${pageContext.servletContext.contextPath}/controller?command=deletePlaylist&playlistId=${playlist.id}">
                                        <i class="la la-trash"></i>
                                        <span><fmt:message key="label.delete" bundle="${loc}"/></span>
                                    </a>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>--%>


                <footer id="footer" class="bg-img"></footer>
            </div>
        </div>
    </main>

</div>

<div class="backdrop header-backdrop"></div>
<div class="backdrop sidebar-backdrop"></div>

</body>
</html>