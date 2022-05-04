<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="resourcebundle.pagecontent" var="loc"/>


<form action="${pageContext.servletContext.contextPath}/controller?command=search" method="post" id="searchForm">

    <button type="button" class="btn ion-ios-search"></button>
    <input type="text" placeholder="<fmt:message key="label.search" bundle="${loc}"/>" id="searchInput"
           name="searchInput"
           class="form-control">


    <%--ARTISTS--%>
    <div class="search-card" data-scrollable="true">

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


        <c:if test="${not empty requestScope.songsList}">
            <!-- Begin | Search Result List -->
            <div class="mb-3">
                <!-- Begin | Search Result List Header -->
                <div class="d-flex">
                    <span class="text-uppercase mr-auto font-weight-bold text-dark">
                        <fmt:message key="label.track" bundle="${loc}"/>
                    </span>
                    <a href="${pageContext.servletContext.contextPath}/controller?command=home">
                        <fmt:message key="label.viewAll" bundle="${loc}"/>
                    </a>
                </div>
                <!-- End | Search Result List Header -->
                <hr>
                <!-- Begin | Result List -->
                <div class="row">
                    <div class="col-xl-4 col-md-6 col-12">
                        <div class="custom-card mb-3">
                            <table>
                                <c:forEach items="${requestScope.songsList}" var="song">
                                    <tr>
                                        <td>
                                            <div class="custom-card--inline-img">
                                                <img src="${pageContext.request.contextPath}/resource/img/artists/${song.album.artist.imagePath}"
                                                     class="card-img--radius-sm" alt="${song.album.artist.imagePath}">
                                            </div>
                                        </td>
                                        <td>
                                            <div class="custom-card--inline-desc">
                                                <p class="text-truncate mb-0"><c:out value="${ song.trackName }"/></p>
                                                <p class="text-truncate text-muted font-sm"><c:out
                                                        value="${ song.album.artist.artistName }"/></p>
                                            </div>
                                        </td>

                                        <td>
                                            <audio preload="auto" controls>
                                                <source src='${pageContext.request.contextPath}/resource/songs/${song.trackPath}'/>
                                            </audio>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- End | Result List -->
            </div>
            <!-- End | Search Result List -->
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
                                            <img src="resource/img/album1.jpg" alt="album1.jpg"
                                                 class="card-img--radius-md">
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
</form>
