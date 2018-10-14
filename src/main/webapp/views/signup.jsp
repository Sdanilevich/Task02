<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="include/head.jsp" %>
<body>
<div >
    <%@ include file="include/menu.jsp" %>
    <form  id ="form_signup" method="post" action="do?command=signup&local=${local}">
             <!-- Form Name -->
            <legend>${registrationTitle}:</legend>
            <!-- Text input-->
            <div style="padding-top: 20px;">
                <label for = "login">${registrationLogin}</label>
                <div >
                    <input id="login" name="login" value="" type="text" placeholder="" required="">
                </div>
            </div>

                        <!-- Password input-->
             <div style="padding-top: 20px;">
                 <label for="password">${registrationPassword}</label>
                 <div >
                     <input id="password" name="password" value="" type="password" placeholder="" required="">
                     <span class="help-block"></span>
                 </div>
             </div>

            <div style="padding-top: 20px;">
                <label for="name">${registrationName}</label>
                <div>
                    <input id="name" name="name" value="" type="text" placeholder="" required="">
                    <span ></span>
                </div>
            </div>
            <div style="padding-top: 20px;">
                <label  for="name">${registrationEmail}</label>
                <div >
                    <input id="email" name="email" value="" type="email" placeholder="" required="">
                    <span ></span>
                </div>
            </div>
              <!-- Button -->
            <div style="padding-top: 20px;">
                <label for="signupbutton"></label>
                <div>
                    <button id="signupbutton" name="signupbutton">${registrationButton}</button>
                </div>
            </div>
    </form>

</div>
</body>
</html>




