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


    <main id="pageWrapper">

        <jsp:include page="../fragment/header.jsp">
            <jsp:param name="page" value="comments"/>
        </jsp:include>

        <div class="banner bg-song"></div>

        <div class="main-container" id="appRoute">

            <%-- ALERTS!!!--%>
            <jsp:include page="../fragment/alerts.jsp"/>

            <div class="section">

                <c:set var="song" value="${requestScope.song}"/>
                <div class="text-dark custom-card--inline">
                    <table>
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
                    </table>
                </div>


                <c:set var="pix" value="40"/>
                <c:set var="user" value="${sessionScope.user}"/>

                <div class="heading">
                    <div class="d-flex flex-wrap align-items-end">
                        <div class="flex-grow-1">
                            <h4><fmt:message key="label.comments" bundle="${loc}"/></h4>
                        </div>
                    </div>
                    <hr>
                </div>


                <c:forEach items="${requestScope.comments}" var="comment" varStatus="loop">
                    <div class="d-flex mb-4" style="margin-left: ${comment.depth * pix}">
                        <div class="pl-3 flex-grow-1 flex-basis-0">
                            <span class="d-block font-weight-bold mb-1">${comment.user.firstName} ${comment.user.lastName}</span>
                            <p class="text-truncate text-muted font-sm">
                                <fmt:message key="label.posted" bundle="${loc}"/>
                                <fmt:formatDate type="both" value='${comment.date}'/>
                            </p>

                            <c:choose>
                                <c:when test="${empty comment.text}">
                                    <p class="text-truncate text-muted font-sm"><fmt:message key="label.deletedComment"
                                                                                             bundle="${loc}"/></p>
                                </c:when>
                                <c:otherwise>
                                    <p class="mb-2" id="comment${loop.index}">${comment.text}</p>
                                </c:otherwise>
                            </c:choose>

                            <input type="hidden" id="emptyText" value="${comment.text}">
                            <c:if test="${user.role != 'ADMIN'}">
                                <a onclick="copyValueTo('emptyText', 'replyComment'.concat(${loop.index}))"
                                   id="${loop.index}" class="chooserClassReply btn p-0" data-show="${loop.index}">
                                    <i class="la la-reply"></i>
                                    <fmt:message key="label.reply" bundle="${loc}"/>
                                </a>
                            </c:if>
                            <c:if test="${user.login == comment.user.login && not empty comment.text || user.role == 'ADMIN'}">
                                <a href="${pageContext.servletContext.contextPath}/controller?command=deleteComment&commentId=${comment.id}&thread=false"
                                   class="btn p-0">
                                    <i class="la la-trash"></i>
                                    <fmt:message key="label.delete" bundle="${loc}"/>
                                </a>

                                <a onclick="copyValueTo('comment'.concat(${loop.index}), 'replyComment'.concat(${loop.index}))"
                                   id="${loop.index}" class="chooserClass btn p-0" data-show="${loop.index}">
                                    <i class="la la-edit"></i>
                                    <fmt:message key="label.edit" bundle="${loc}"/>
                                </a>
                            </c:if>

                            <c:if test="${not loop.last && user.role == 'ADMIN' && requestScope.comments[loop.index+1].depth > comment.depth}">
                                <a href="${pageContext.servletContext.contextPath}/controller?command=deleteComment&commentId=${comment.id}&thread=true"
                                   class="btn p-0">
                                    <i class="la la-trash"></i>
                                    <fmt:message key="label.deleteThread" bundle="${loc}"/>
                                </a>
                            </c:if>

                        </div>

                        <div id="d_${loop.index}" style="display: none">
                            <form action="${pageContext.servletContext.contextPath}/controller?command=addComment&commentId=${comment.id}&songId=${song.id}&parentId=${comment.id}"
                                  method="post">
                                <textarea name="textComment" id="replyComment${loop.index}" cols="30" rows="5"
                                          class="form-control"
                                          maxlength="700"></textarea>
                                <div class="text-right mt-2">
                                    <button type="submit" class="btn btn-info"><fmt:message key="label.comment"
                                                                                            bundle="${loc}"/></button>
                                </div>
                            </form>
                        </div>


                        <div id="rep_${loop.index}" style="display: none">
                        <form action="${pageContext.servletContext.contextPath}/controller?command=addComment&songId=${song.id}&parentId=${comment.id}"
                              method="post">
                                <textarea name="textComment" cols="30" rows="5"
                                          class="form-control"
                                          maxlength="700"></textarea>
                            <div class="text-right mt-2">
                                <button type="submit" class="btn btn-info"><fmt:message key="label.comment"
                                                                                        bundle="${loc}"/></button>
                            </div>
                        </form>
                    </div>

                    </div>
                </c:forEach>


                <c:if test="${user.role != 'ADMIN'}">
                    <div class="mb-4">
                        <form action="${pageContext.servletContext.contextPath}/controller?command=addComment&songId=${song.id}"
                              method="post">
                        <textarea name="textComment" id="comment" cols="30" rows="5" class="form-control"
                                  maxlength="700"></textarea>
                            <div class="text-right mt-2">
                                <button class="btn btn-info">Comment</button>
                            </div>
                        </form>
                    </div>
                </c:if>

            </div>


        </div>
        <footer id="footer" class="bg-img"></footer>

    </main>
</div>

<div class="backdrop header-backdrop"></div>
<div class="backdrop sidebar-backdrop"></div>

</body>
</html>


<script type="text/javascript">
    $(document).ready(function () {
        $(".chooserClass").click(function () {
            var id = $(this).attr('id');

            var shadow = document.getElementById('d_'.concat(id));

            if (shadow.style.display !== 'none') {
                shadow.style.display = "none";
            } else {
                shadow.style.display = "block";
            }
        });

        $(".chooserClassReply").click(function () {
            var id = $(this).attr('id');

            var shadow = document.getElementById('rep_'.concat(id));

            if (shadow.style.display !== 'none') {
                shadow.style.display = "none";
            } else {
                shadow.style.display = "block";
            }
        });
    });

    if (window.history.replaceState) {
        window.history.replaceState(null, null, window.location.href);
    }

    function copyValueTo(fromElemId, toElemId) {
        document.getElementById(toElemId).value = document.getElementById(fromElemId).textContent;
    }

</script>
