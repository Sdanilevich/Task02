<%@ page contentType="text/html;charset=UTF-8"
         language="java" pageEncoding="UTF-8" %>
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
    <div class="right">
        <a style = "color:#800000" href="do?command=userOrder&UserId=${user.id}">${user.name}</a>
        <a  href=${pageContext.request.contextPath}/do?<%=currUrlRu%>>RU</a>
        <a  href=${pageContext.request.contextPath}/do?<%=currUrlEn%> >ENG</a>
        <a  href="do?command=Login">${menuLogin}</a>
        <a  href="do?command=SignUp">${menuSignUp}</a>
     </div>
</div>
