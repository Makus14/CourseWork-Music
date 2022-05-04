<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="resourcebundle.pagecontent" var="loc"/>
<jsp:useBean id="date" class="java.util.Date"/>
<fmt:formatDate value="${date}" pattern="yyyy" var="currentYear"/>

<html>
<head>
    <title>Music Time</title>

    <!-- Favicon -->
    <link href='<c:url value="/resource/img/purple-img.png"/>' rel="icon"/>

    <!-- IOS Touch Icons -->

    <!-- Styles -->
    <link href='<c:url value="/assets/css/vendors.bundle.css"/>' rel="stylesheet" type="text/css"/>
    <link href='<c:url value="/assets/css/styles.bundle.css"/>' rel="stylesheet" type="text/css"/>
    <link href='<c:url value="/assets/plugin/chosen.css"/>' rel="stylesheet" type="text/css"/>

    <!-- Google fonts -->
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:100,300,400,700" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<jsp:include page="../fragment/loading.jsp"/>

<div id="wrapper" data-scrollable="true">

    <jsp:include page="../fragment/left-aside-admin.jsp"/>

    <!-- Begin | Page Wrapper [[ Find at scss/framework/base/wrapper/wrapper.scss ]] -->
    <main id="pageWrapper">

        <jsp:include page="../fragment/header.jsp">
            <jsp:param name="page" value="addAlbumPage"/>
        </jsp:include>


        <!-- Begin | Main Container [[ Find at scss/base/core.scss ]] -->
        <div class="main-container under-banner-content" id="appRoute">

            <%-- ALERTS!!!--%>
            <jsp:include page="../fragment/alerts.jsp">
                <jsp:param name="page" value="addAlbumPage"/>
            </jsp:include>

            <div class="row section">
                <div class="col-xl-8 col-md-10 mx-auto">
                    <div class="card">
                        <div class="card-header">
                            <h6 class="card-title mb-0"><fmt:message key="label.createAlbum" bundle="${loc}"/></h6>
                        </div>
                        <div class="card-body">
                            <form method="post"
                                  action="${pageContext.servletContext.contextPath}/controller?command=addAlbum">
                                <div class="form-row form-group">
                                    <label for="albumName" class="col-md-4 text-md-right col-form-label">
                                        <fmt:message key="label.albumName" bundle="${loc}"/>
                                    </label>
                                    <div class="col-md-7">
                                        <input type="text" id="albumName" name="albumName" class="form-control" required
                                               autocomplete="off" maxlength="40">
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label for="year" class="col-md-4 text-md-right col-form-label">
                                        <fmt:message key="label.year" bundle="${loc}"/>
                                    </label>
                                    <div class="col-md-7">
                                        <input type="number" id="year" name="year" value="2020" min="1150"
                                               max="${currentYear}" step="1" required>
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label class="col-md-4 text-md-right col-form-label">
                                        <fmt:message key="label.artistName" bundle="${loc}"/>
                                    </label>
                                    <div class="col-md-7">
                                        <select data-placeholder="<fmt:message key="label.chooseArtist" bundle="${loc}"/>"
                                                class="form-control chosen-select"
                                                name="artistsName" required>
                                            <c:forEach var="item" items="${requestScope.artistsName}">
                                                <option>${item.artistName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="card-footer">
                                    <div class="row">
                                        <div class="col-md-4"></div>
                                        <div class="col-md-7">
                                            <button type="submit" class="btn btn-brand btn-air">
                                                <fmt:message key="label.saveAlbum" bundle="${loc}"/>
                                            </button>
                                            <button type="reset" class="btn btn-outline-secondary">
                                                <fmt:message key="label.clearForm" bundle="${loc}"/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>


        <footer id="footer" class="bg-img"></footer>

    </main>

    <script src="${pageContext.request.contextPath}/assets/js/vendors.bundle.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/scripts.bundle.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/chosen.jquery.js"></script>
    <script src="${pageContext.request.contextPath}/assets/plugin/chosen.proto.js"></script>
    <script>
        $(document).ready(function () {
            $(".chosen-select").chosen();
        });

        if (window.history.replaceState) {
            window.history.replaceState(null, null, window.location.href);
        }
    </script>

</div>
</body>
</html>
