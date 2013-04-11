<%--
    Document   : AdminActRes
    Created on : 2013-4-11, 14:39:01
    Author     : Diluka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String result = request.getParameter("result");
	String from = request.getParameter("from");
	String actionType = request.getParameter("actionType");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>操作结果</title>
		<link href="css/maintheme.css" rel="stylesheet" type="text/css">
		<link href="css/admin.css" rel="stylesheet" type="text/css">
    </head>
    <body>
		<table width="95%" border="0" align="center">
			<tr>
				<td align="center"><jsp:include flush="true" page="AdminHeader.jsp"/></td>
			</tr>
			<tr>
				<td align="center"><div id="infobox">
						<table width="50%" border="1">
							<tr>
								<th colspan="2" align="center">操作结果</th>
							</tr>
							<tr>
								<td width="50%" align="center">操作</td>
								<td width="50%" align="center"><%=actionType%></td>
							</tr>
							<tr>
								<td width="50%" align="center">结果</td>
								<td width="50%" align="center"><%=result%></td>
							</tr>
							<tr>
								<td colspan="2" align="center"><a href="<%=from%>">返回</a></td>
							</tr>
						</table>
					</div></td>
			</tr>
			<tr>
				<td align="center"><jsp:include flush="true" page="AdminFooter.jsp"/></td>
			</tr>
		</table>

    </body>
</html>
