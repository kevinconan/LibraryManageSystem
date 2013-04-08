<%-- 
    Document   : LoginMain
    Created on : 2013-4-8, 17:41:26
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <center><h1>欢迎登陆</h1><br>
            登陆成功！<%= session.getAttribute("bcid")%>
            <hr>
            <a></a>
        </center>
    </body>
</html>
