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
    <link href='<c:url value="/assets/plugin/chosen.css"/>' rel="stylesheet" type="text/css"/>
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

    <!-- Begin | Page Wrapper  -->
    <main id="pageWrapper">

        <jsp:include page="../fragment/header.jsp">
            <jsp:param name="page" value="editSongPage"/>
        </jsp:include>

        <!-- Begin | Main Container [[ Find at scss/base/core.scss ]] -->
        <div class="main-container under-banner-content" id="appRoute">

            <%-- ALERTS!!!--%>
            <jsp:include page="../fragment/alerts.jsp"/>

            <div class="row section">
                <div class="col-xl-10 mx-auto">

                    <c:set var="song" value="${requestScope.song}"/>


                    <div class="card">
                        <div class="card-header">
                            <h6 class="card-title mb-0">
                                <fmt:message key="label.editSong" bundle="${loc}"/>
                            </h6>
                        </div>
                        <div class="card-body">
                            <form method="post" enctype="multipart/form-data"
                                  action="${pageContext.servletContext.contextPath}/controller?command=addSong">
                                <div class="form-row form-group">
                                    <label for="songName" class="col-md-4 text-md-right col-form-label">
                                        <fmt:message key="label.songName" bundle="${loc}"/>
                                    </label>
                                    <div class="col-md-7">
                                        <input type="text" id="songName" name="songName" class="form-control"
                                               value="${song.trackName}" required maxlength="40">
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label class="col-md-4 text-md-right col-form-label">
                                        <fmt:message key="label.genre" bundle="${loc}"/>
                                    </label>
                                    <div class="col-md-7">
                                        <select data-placeholder="<fmt:message key="label.chooseGenre" bundle="${loc}"/>"
                                                class="form-control chosen-select"
                                                name="genre" required>
                                            <option selected>${sessionScope.genres.get(song.genre)}</option>
                                            <c:forEach var="genre" items="${sessionScope.genres}">
                                                <option>${genre.value}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label for="songFile" class="col-md-4 text-md-right col-form-label">
                                        <fmt:message key="label.songFile" bundle="${loc}"/>
                                    </label>
                                    <div class="col-md-7">
                                        <div class=" custom-file">
                                            <input type="file" accept="audio/*" class="custom-file-input"
                                                   id="songFile" name="songFile"
                                                   required>
                                            <label class="custom-file-label" for="songFile" id="file">
                                                ${song.trackPath}
                                            </label>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label class="col-md-4 text-md-right col-form-label">
                                        <fmt:message key="label.artistName" bundle="${loc}"/>
                                    </label>
                                    <div class="col-md-7">
                                        <select data-placeholder="${song.album.artist.artistName}"
                                                required class="form-control chosen-select" multiple name="artistsName">
                                            <option selected>${song.album.artist.artistName}</option>
                                            <c:forEach var="item" items="${requestScope.artistsName}">
                                                <option>${item.artistName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-row form-group">
                                    <label class="col-md-4 text-md-right col-form-label">
                                        <fmt:message key="label.album" bundle="${loc}"/>
                                    </label>
                                    <div class="col-md-7">
                                        <select data-placeholder="${song.album.albumName}"
                                                class="form-control chosen-select" name="album" required>
                                            <option><fmt:message key="label.single" bundle="${loc}"/></option>
                                            <c:forEach var="item" items="${requestScope.albums}">
                                                <option>${item.albumName}</option>
                                            </c:forEach>

                                        </select>
                                    </div>
                                </div>
                                <div class="card-footer">
                                    <div class="row">
                                        <div class="col-md-4"></div>
                                        <div class="col-md-7">
                                            <button type="submit" class="btn btn-brand btn-air">
                                                <fmt:message key="label.saveSong" bundle="${loc}"/>
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
    <!-- End | Page Wrapper -->
</div>
<!-- Back Drop -->
<div class="backdrop header-backdrop"></div>
<div class="backdrop sidebar-backdrop"></div>


<script src="${pageContext.request.contextPath}/assets/js/vendors.bundle.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/scripts.bundle.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugin/chosen.jquery.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugin/chosen.proto.js"></script>
<script>
    $(".chosen-select").chosen();

    $('#file').click(function () {
        $("input[type='file']").trigger('click');
    })

    $("input[type='file']").change(function () {
        $('#file').text(this.value.replace(/C:\\fakepath\\/i, ''))
        $('.customform-control').hide();
    })

    if (window.history.replaceState) {
        window.history.replaceState(null, null, window.location.href);
    }
</script>


</body>
</html>

