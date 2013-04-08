<%-- 
    Document   : test
    Created on : 2013-4-8, 13:17:18
    Author     : Diluka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <style type="text/css">
    body h1 {
	text-align: center;
}
    </style>
    </head>
    <body>
    <h1>测试页面，添加数据</h1>
    <table width="80%" border="1" align="center">
      <tr>
        <td><form name="form1" method="post" action="">
          <table width="100%" border="1">
            <tr>
              <td colspan="2" align="center">添加用户</td>
            </tr>
            <tr>
              <td>用户ID</td>
              <td><input type="text" name="uid"></td>
            </tr>
            <tr>
              <td>密码</td>
              <td><input type="password" name="pw"></td>
            </tr>
            <tr>
              <td colspan="2"><input type="submit" name="button" id="button" value="提交">
              <input type="reset" name="button2" id="button2" value="重置"></td>
            </tr>
          </table>
        </form></td>
      </tr>
      <tr>
        <td><form name="form2" method="post" action="BookCl?type=addbook">
          <table width="100%" border="1">
            <tr>
              <td colspan="2">添加书籍</td>
            </tr>
            <tr>
              <td>ISBN</td>
              <td><input type="text" name="isbn" ></td>
            </tr>
            <tr>
              <td>书名</td>
              <td><input type="text" name="bookname" ></td>
            </tr>
            <tr>
              <td colspan="2"><input type="submit" name="button3" id="button3" value="提交">
              <input type="reset" name="button4" id="button4" value="重置"></td>
            </tr>
          </table>
        </form></td>
      </tr>
    </table>
    <p>&nbsp;</p>
    </body>
</html>
