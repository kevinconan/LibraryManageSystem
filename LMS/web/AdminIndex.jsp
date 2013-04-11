<%--
    Document   : adminmain
    Created on : 2013-4-7, 22:16:28
    Author     : Kevin
--%>

<%@page import="net.kevinconan.model.AdminBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	AdminBean ab = (AdminBean) request.getSession().getAttribute("AdminLoginInfo");
	if (ab == null) {
		response.sendRedirect("AdminLogin.jsp");
	}

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>管理员首页</title>
		<link href="css/maintheme.css" rel="stylesheet" type="text/css">
		<link href="css/admin.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
			function bookout() {
				document.bookio.actionType.value = "借书";
				return window.confirm("确认登记借书？");
			}

			function bookin() {
				document.bookio.actionType.value = "还书";
				return window.confirm("确认登记还书？");
			}
		</script>
    </head>
    <body>
		<table width="95%" border="0" align="center">
			<tr>
				<td align="center"><jsp:include flush="true" page="AdminHeader.jsp"/></td>
			</tr>
			<tr>
				<td align="center"><div id="admininfo">
						<table width="100%" border="0">
							<tr>
								<td colspan="2"><div id="title">管理员信息</div></td>
							</tr>
							<tr>
								<td colspan="2">&nbsp;</td>
							</tr>
							<tr>
								<td width="40%" align="right">管理员ID：</td>
								<td><%=ab.getADID()%></td>
							</tr>
							<tr>
								<td width="40%" align="right">姓名：</td>
								<td><%=ab.getANAME()%></td>
							</tr>
							<tr>
								<td width="40%" align="right">性别：</td>
								<td><%=ab.getAGENDER()%></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
						</table>
					</div>
					<div id="bookcheck">
						<form name="bookio" method="post" action="BookIO">
							<table width="100%" border="0">
								<tr>
									<th colspan="2" align="center">借书/还书登记</th>
								</tr>
								<tr>
									<td>ISBN: </td>
									<td><input name="isbn" type="text" id="isbn" maxlength="20"></td>
								</tr>
								<tr>
									<td>BCID: </td>
									<td><input name="bcid" type="text" id="bcid" maxlength="10"></td>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input type="hidden" name="actionType" value="">
										<input type="submit" name="button" id="button" value="借书登记" onClick="return bookout();">
										<input type="submit" name="button2" id="button2" value="还书登记" onClick="return bookin();">
									</td>
								</tr>
							</table>
						</form>
					</div></td>
			</tr>
			<tr>
				<td align="center"><jsp:include flush="true" page="AdminFooter.jsp"/></td>
			</tr>
		</table>

	</body>
</html>
