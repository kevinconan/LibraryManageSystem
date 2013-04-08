<%-- 
    Document   : AdminLogin
    Created on : 2013-4-7, 20:52:48
    Author     : Kevin
--%>

<%@page contentType="text/html" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>管理员登陆</h1>
        <%
        
        String info=request.getParameter("info");
        
        if(info !=null){
            out.println("用户名密码错误！");
        }
            
        %>
        <form Action="AdminLoginChk" Method="Post">
            管理员帐号：<input type="text" name="username" />
            管理员密码：<input type="password" name="password" />
            <input type="submit" value="登陆" />
        </form>
            
    </body>
</html>
