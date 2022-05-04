<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="resourcebundle.pagecontent" var="loc"/>

<aside id="sidebar" class="sidebar-primary">

    <!-- Begin | Sidebar Header -->
    <div class="sidebar-header d-flex align-items-center">
        <a href="${pageContext.servletContext.contextPath}/controller?command=home" class="brand">
            <img src="${pageContext.request.contextPath}/resource/img/purple-img.png" alt="listen-app">
        </a>

        <button type="button" class="btn p-0 ml-auto" id="hideSidebar">
            <i class="ion-md-arrow-back h3"></i>
        </button>

        <button type="button" class="btn toggle-menu" id="toggleSidebar">
            <span></span>
            <span></span>
            <span></span>
        </button>
    </div>
    <!-- End | Sidebar Header -->

    <!-- Begin | Navbar  -->
    <nav class="navbar">
        <ul class="navbar-nav" data-scrollable="true">
            <li class="nav-item nav-header"><fmt:message key="label.browseMusic" bundle="${loc}"/></li>
            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/controller?command=home" class="nav-link active"><i class="la la-home"></i><span>
                    <fmt:message key="label.home" bundle="${loc}"/>
                </span></a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/controller?command=genres" class="nav-link"><i class="la la-diamond"></i><span>
                    <fmt:message key="label.genres" bundle="${loc}"/>
                </span></a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/controller?command=artists" class="nav-link"><i class="la la-microphone"></i><span>
                    <fmt:message key="label.artists" bundle="${loc}"/>
                </span></a>
            </li>

<c:if test="${sessionScope.user.subscription}">

            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/controller?command=playlistsPage&all=1" class="nav-link"><i class="la la-headphones"></i><span>
                    <fmt:message key="label.playlists" bundle="${loc}"/>
                </span></a>
            </li>

            <li class="nav-item nav-header">
                <fmt:message key="lable.yourMusic" bundle="${loc}"/>
            </li>
            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/controller?command=favorites" class="nav-link"><i class="la la-heart-o"></i><span>
                    <fmt:message key="lable.favorites" bundle="${loc}"/>
                </span></a>
            </li>
            <li class="nav-item">
                <a href="${pageContext.servletContext.contextPath}/controller?command=playlistsPage" class="nav-link"><i class="la la-history"></i><span>
                    <fmt:message key="label.myPlaylists" bundle="${loc}"/>
                </span></a>
            </li>
        </ul>
</c:if>
    </nav>
    <!-- End | Navbar -->

</aside>
<!-- End | Sidebar -->
