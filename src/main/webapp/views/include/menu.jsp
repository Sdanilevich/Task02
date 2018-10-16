<%@ page contentType="text/html;charset=UTF-8"
         language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String currUrl = request.getQueryString();
    currUrl = currUrl.replaceAll("&local=En","");
    currUrl = currUrl.replaceAll("&local=Ru","");
    String currUrlRu = currUrl.concat("&local=Ru");
    String currUrlEn = currUrl.concat("&local=En");
%>
<div class="wrap">
    <div class="left">
        <a href="do?command=index">${menuMain}</a>
        <a href="do?command=SearchApartment&newSearch=true">${menuSearch}</a>
    </div>
    <c:set var = "userSession" scope = "session" value = "${user}"/>

    <div class="right">
        <a style = "color:#800000" href="do?command=userOrder&UserId=${user.id}">${user.name}</a>
        <a  href=${pageContext.request.contextPath}/do?<%=currUrlRu%>>RU</a>
        <a  href=${pageContext.request.contextPath}/do?<%=currUrlEn%> >ENG</a>
        <c:choose>
            <c:when test="${empty userSession}">
                <a  href="do?command=Login">${menuLogin}</a>
            </c:when>
            <c:otherwise>
                <a  href="do?command=Signout">${menuSignOut}</a>
            </c:otherwise>
        </c:choose>

        <a  href="do?command=SignUp">${menuSignUp}</a>
     </div>
</div>
