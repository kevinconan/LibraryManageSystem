<%--
    Document   : index
    Created on : 2013-4-7, 19:44:32
    Author     : Kevin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String identity;
	if (request.getSession().getAttribute("AdminLoginInfo") != null) {
		identity = "admin";
	} else {
		identity = "user";
	}

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome To Kevin's Library!</title>
		<link href="css/maintheme.css" rel="stylesheet" type="text/css">
    </head>
    <body>

		<table width="90%" border="0" align="center" class="maintable" id="main">
			<tr><%
				if (identity.equals("admin")) {
				%><td><jsp:include flush="true" page="AdminHeader.jsp"/></td><%						} else {
				%><td><jsp:include flush="true" page="header.jsp"/></td><%							}
				%>
			</tr>
			<tr>
				<td><table width="100%" border="0" class="maintable">
						<tr>
							<td><img src="images/logo.png" width="200" height="236"></td>
						</tr>
						<tr>
							<td><div id="searchbox"><form name="form1" method="post" action="SearchBook">
										<input name="searchText" type="text" class="components" id="searchText" size="80">
										<input name="button" type="submit" class="components" id="button" value="开始搜索">
									</form></div></td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<%
					if (identity.equals("admin")) {
				%><td><jsp:include flush="true" page="AdminFooter.jsp"/></td><%						} else {
				%><td><jsp:include flush="true" page="footer.jsp"/></td><%							}
				%>
			</tr>
		</table>

	</body>
</html>
