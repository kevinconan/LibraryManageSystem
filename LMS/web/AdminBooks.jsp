<%--
    Document   : AdminBooks
    Created on : 2013-4-11, 9:18:00
    Author     : Kevin
--%>

<%@page import="net.kevinconan.model.AdminBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	AdminBean ab = (AdminBean) request.getSession().getAttribute("AdminLoginInfo");
	if (ab == null) {
		response.sendRedirect("AdminLogin.jsp");
	}

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>书籍管理</title>
		<link href="css/maintheme.css" rel="stylesheet" type="text/css">
		<link href="css/admin.css" rel="stylesheet" type="text/css">

		<style type="text/css">

			input.groovybutton
			{
				font-weight:bold;
				color:#00FFFF;
				background-color:#999999;
				border-style:dashed;
			}

		</style>

		<script language="javascript">

			function goLite(FRM, BTN)
			{
				window.document.forms[FRM].elements[BTN].style.color = "#00FF99";
				window.document.forms[FRM].elements[BTN].style.backgroundColor = "#CCCCCC";
				window.document.forms[FRM].elements[BTN].style.borderStyle = "dotted";
				window.document.forms[FRM].elements[BTN].style.borderColor = "";
			}

			function goDim(FRM, BTN)
			{
				window.document.forms[FRM].elements[BTN].style.color = "#00FFFF";
				window.document.forms[FRM].elements[BTN].style.backgroundColor = "#999999";
				window.document.forms[FRM].elements[BTN].style.borderStyle = "dashed";
				window.document.forms[FRM].elements[BTN].style.borderColor = "";
			}

		</script>

    </head>
    <body>
		<table width="95%" border="0" align="center">
			<tr>
				<td align="center"><jsp:include flush="true" page="AdminHeader.jsp"/></td>
			</tr>
			<tr>
				<td><table width="100%" border="0" class="maintable">

						<tr>
							<td>
								<form name="groovyform" action="updateBook.jsp?type=add" method="post">
									<input
										type="submit"
										name="newbook"
										class="groovybutton"
										value="添加新书"
										title=""
                                        style="font-size:100px;margin:50px;"
										onMouseOver="goLite(this.form.name, this.name)"
										onMouseOut="goDim(this.form.name, this.name)">
								</form>

								<div id="searchbox"><form name="form1" method="post" action="SearchBook">
										<input name="searchText" type="text" class="components" id="searchText" size="80">
										<input name="button" type="submit" class="components" id="button" value="开始搜索">
									</form></div></td>
						</tr>


					</table></td>
			</tr>
			<tr>
				<td align="center"><jsp:include flush="true" page="AdminFooter.jsp"/></td>
			</tr>
		</table>

	</body>
</html>
