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

<div id="wrapper" data-scrollable="true">

    <c:if test="${sessionScope.user.role.accountRole == 'CLIENT'}">
        <jsp:include page="../fragment/left-aside-client.jsp"/>
    </c:if>
    <c:if test="${sessionScope.user.role.accountRole == 'ADMIN'}">
        <jsp:include page="../fragment/left-aside-admin.jsp"/>
    </c:if>

    <!-- Begin | Page Wrapper] -->
    <main id="pageWrapper">

        <jsp:include page="../fragment/header.jsp">
            <jsp:param name="page" value="addToPlaylistPage"/>
        </jsp:include>

        <!-- Begin | Main Container [[ Find at scss/base/core.scss ]] -->
        <div class="main-container under-banner-content" id="appRoute">

            <%-- ALERTS!!!--%>
            <jsp:include page="../fragment/alerts.jsp">
                <jsp:param name="page" value="addAlbumPage"/>
            </jsp:include>

            <div class="popup-fade">
                <div class="popup">
                    <div class="card">
                        <div class="card-header">
                            <h6 class="card-title mb-0"><fmt:message key="label.addToPlaylist" bundle="${loc}"/></h6>
                        </div>
                        <div class="card-body">
                            <form action="${pageContext.servletContext.contextPath}/controller?command=addToPlaylist&songId=${param.songId}"
                                  method="post">
                                <div class="form-row form-group">
                                    <select name="playlistName" class="form-control" style="width: 300px">

                                        <option value="" disabled selected hidden><fmt:message key="label.choosePlaylist" bundle="${loc}"/></option>

                                        <c:forEach var="item" items="${requestScope.playlistList}">
                                            <option>${item.playlistName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-brand btn-air btn-center"><fmt:message key="label.addToPlaylist" bundle="${loc}"/></button>
                            </form>

                            <form action="${pageContext.servletContext.contextPath}/controller?command=createAndAddToPlaylist&songId=${param.songId}"
                                  method="post">

                                <div class="form-row form-group">
                                    <input type="text" class="form-control" placeholder="<fmt:message key="label.createNewPlaylist" bundle="${loc}"/>"
                                           id="playlistName" name="playlistName" style="width: 300px"
                                           autocomplete="off" maxlength="40">
                                </div>
                                <button type="submit" class="btn btn-brand btn-air btn-center"><fmt:message key="label.createAndAdd" bundle="${loc}"/></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>


        </div>

        <footer id="footer" class="bg-img"></footer>

    </main>
    <!-- End | Page Wrapper -->
</div>
<!-- Back Drop -->
<div class="backdrop header-backdrop"></div>
<div class="backdrop sidebar-backdrop"></div>

<!-- Scripts -->

<script>
    if ( window.history.replaceState ) {
        window.history.replaceState( null, null, window.location.href );
    }
</script>

</body>
</html>
