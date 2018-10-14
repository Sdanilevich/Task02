 <%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
<div>
    <%@ include file="include/menu.jsp" %>
    <form id="form_login" method="post" action="do?command=login" style="border: 0px;">
        <fieldset>

            <!-- Form Name -->
            <legend>${loginTitle}</legend>

            <!-- Text input-->
            <div style="padding-top: 10px">
                <label for="login">${loginName}</label>
                <div>
                    <input id="login" name="login" value="admin" type="text" placeholder=""
                           required="">
                </div>
            </div>


            <!-- Password input-->
            <div style="padding-top: 10px">
                <label  for="password">${loginPassword}</label>
                <div >
                    <input id="password" name="password" value="123" type="password" placeholder=""
                           required="">
                </div>

            </div>

            <!-- Button -->
            <div style="padding-top: 10px">
                <label  for="signupbutton"></label>
                <div >
                    <button id="signupbutton" name="signupbutton">${loginButton}</button>
                </div>
            </div>

        </fieldset>
    </form>
</div>
</body>
</html>

