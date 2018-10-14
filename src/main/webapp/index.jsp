<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="views/include/head.jsp" %>
<body>

     <div class="wrap">
         <div class="left">
             <a href=".">Главная</a>
             <a href="do?command=SearchApartment">Поиск</a>
         </div>
         <div class="right">
             <a style = "color:#800000" href="do?command=userOrder&newSearch=true">${user.name}</a>
             <a  href="do?command=index&local=Ru">RU</a>
             <a  href="do?command=index&local=En">ENG</a>
             <a  href="do?command=Login">Вход</a>
             <a  href="do?command=SignUp">Регистрация</a>
         </div>
     </div>

</body>
</html>

