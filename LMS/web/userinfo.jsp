<%--
    Document   : userinfo
    Created on : 2013-4-8, 21:56:02
    Author     : Diluka
--%>

<%@page import="net.kevinconan.model.BookCardBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	BookCardBean bcb = (BookCardBean) request.getSession().getAttribute("loginInfo");
	if (bcb == null) {
		response.sendRedirect("Login.jsp?from=userinfo");
		bcb = new BookCardBean();
	}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>借书卡信息</title>
		<link href="css/maintheme.css" rel="stylesheet" type="text/css">
    </head>
    <body>
		<table width="95%" border="0" align="center">
			<tr>
				<td align="center"><jsp:include flush="true" page="header.jsp"/></td>
			</tr>
			<tr>
				<td align="center"><div id="infobox"><table width="50%" border="1">
							<tr>
								<td colspan="2" align="center"><div id="title">借书卡信息</div></td>
							</tr>
							<tr>
								<td width="40%" align="right">借书卡ID：</td>
								<td align="left"><%=bcb.getBCID()%></td>
							</tr>
							<tr>
								<td width="40%" align="right">用户姓名：</td>
								<td align="left"><%=bcb.getBNAME()%></td>
							</tr>
							<tr>
								<td width="40%" align="right">用户性别：</td>
								<td align="left"><%=bcb.getBSEX()%></td>
							</tr>
							<tr>
								<td width="40%" align="right">借阅权限：</td>
								<td align="left"><%=bcb.getBAUTH()%></td>
							</tr>
							<tr>
								<td width="40%" align="right">办理时间：</td>
								<td align="left"><%=bcb.getBDATE()%></td>
							</tr>
						</table></div></td>
			</tr>
			<tr>
				<td align="center"><jsp:include flush="true" page="footer.jsp"/></td>
			</tr>
		</table>
    </body>
</html>
