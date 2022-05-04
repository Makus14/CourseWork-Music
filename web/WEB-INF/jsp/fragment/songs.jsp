<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${cookie.language.value}"/>
<fmt:setBundle basename="resourcebundle.pagecontent" var="loc"/>


<div class="section">

    <!-- Begin | Custom List -->
    <div class="section custom-list">
        <!-- Begin | Custom List Item -->
        <div class="custom-list--item">
            <div class="text-dark custom-card--inline">

                <table>
                    <c:forEach items="${requestScope.songs}" var="song">

                        <td>
                            <div class="custom-card--inline-img">
                                <img src="${pageContext.request.contextPath}/resource/img/artists/${song.album.artist.imagePath}"
                                     alt="${song.album.artist.imagePath}"
                                     class="card-img--radius-sm">
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

                        <td>
                            <ul class="custom-card--labels d-flex ml-auto">
                                <li class="dropleft">
                                    <a href=""
                                       class="btn btn-icon-only p-0 w-auto h-auto"
                                       data-toggle="dropdown" aria-haspopup="true"
                                       aria-expanded="false">
                                        <i class="la la-ellipsis-h"></i>
                                    </a>
                                    <ul class="dropdown-menu">

                                        <c:if test="${sessionScope.user.role.accountRole == 'CLIENT' && sessionScope.user.subscription}">
                                            <li class="dropdown-item">
                                                <a href="${pageContext.servletContext.contextPath}/controller?command=addToPlaylist&songId=${song.id}&playlistName=favorite">
                                                    <i class="la la-heart-o"></i>
                                                    <span><fmt:message key="label.favorite" bundle="${loc}"/></span>
                                                </a>
                                            </li>
                                        </c:if>

                                        <c:if test="${sessionScope.user.role.accountRole == 'ADMIN' || sessionScope.user.subscription}">
                                            <li class="dropdown-item">
                                                <a href="${pageContext.servletContext.contextPath}/controller?command=addToPlaylistPage&songId=${song.id}">
                                                    <i class="la la-plus"></i>
                                                    <span><fmt:message key="label.addToPlaylist"
                                                                       bundle="${loc}"/></span>
                                                </a>
                                            </li>
                                        </c:if>

                                        <c:if test="${sessionScope.user.role.accountRole == 'ADMIN'}">
                                            <li class="dropdown-item">
                                                <a href="${pageContext.servletContext.contextPath}/controller?command=deleteSong&songId=${song.id}">
                                                    <i class="la la-trash"></i>
                                                    <span><fmt:message key="label.delete" bundle="${loc}"/></span>
                                                </a>
                                            </li>
                                            <li class="dropdown-item">
                                                <a href="${pageContext.servletContext.contextPath}/controller?command=editSong&songId=${song.id}">
                                                    <i class="la la-edit"></i>
                                                    <span><fmt:message key="label.edit" bundle="${loc}"/></span>
                                                </a>
                                            </li>
                                        </c:if>

                                        <li class="dropdown-item">
                                            <a href="${pageContext.servletContext.contextPath}/controller?command=comments&songId=${song.id}">
                                                <i class="la la-wechat"></i>
                                                <span><fmt:message key="label.comments" bundle="${loc}"/></span>
                                            </a>
                                        </li>

                                    </ul>
                                </li>

                            </ul>
                        </td>


                        </tr>

                    </c:forEach>
                </table>

                <%--For displaying Page numbers.
                The when condition does not display a link for the current page--%>
                <br>
                <table class="page-table">
                    <tr>
                        <%--For displaying Previous link except for the 1st page --%>
                        <c:if test="${requestScope.currentPage != 1}">
                            <td>
                                <a href="${pageContext.servletContext.contextPath}/controller?command=${param.page}&pageNo=${requestScope.currentPage - 1}"
                                   class="for-page"><fmt:message key="label.prev" bundle="${loc}"/></a></td>
                        </c:if>

                        <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${requestScope.currentPage eq i}">
                                    <td>${i}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <a href="${pageContext.servletContext.contextPath}/controller?command=${param.page}&pageNo=${i}"
                                           class="for-page">${i}</a>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <%--For displaying Next link --%>
                        <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                            <td>
                                <a href="${pageContext.servletContext.contextPath}/controller?command=${param.page}&pageNo=${requestScope.currentPage + 1}"
                                   class="for-page"><fmt:message key="label.next" bundle="${loc}"/></a></td>
                        </c:if>

                    </tr>
                </table>

            </div>

        </div>
        <!-- End | Custom List Item -->
    </div>
</div>

<script>
    let audioNL = document.querySelectorAll('audio');
    let audio = Array.apply(null, audioNL);

    audio.forEach(t => {
        let index = audio.indexOf(t);

        t.addEventListener('play', () => {
            audio.forEach(subT => {
                subT !== audio[index] ?
                    (subT.pause(), subT.currentTime = 0) :
                    subT.play()
            })
        })
        t.addEventListener('ended', () => {
            t.currentTime = 0;
            index !== audio.length - 1 ?
                audio[index + 1].play() :
                null
        })
    });
</script>