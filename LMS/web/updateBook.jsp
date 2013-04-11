<%--
    Document   : addbook
    Created on : 2013-4-11, 18:37:06
    Author     : Kevin
--%>

<%@page import="net.kevinconan.model.BookBean"%>
<%@page import="net.kevinconan.model.BookBO"%>
<%@page import="net.kevinconan.model.AdminBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	AdminBean ab = (AdminBean) request.getSession().getAttribute("AdminLoginInfo");
	if (ab == null) {
		response.sendRedirect("AdminLogin.jsp");
	}
	String actionType = request.getParameter("type");
	System.out.println(actionType);
	String titleString = "";
	String buttenString = "";
	String actString = "";
	String readonly = "";
	BookBean bb = new BookBean(true);
	if (actionType.equals("add")) {

		titleString = "添加书籍";
		buttenString = "添加";
		actString = "addbook";
	} else if (actionType.equals("update")) {
		bb = (BookBean) request.getAttribute("bookInfo");
		titleString = "修改书籍";
		buttenString = "修改";
		readonly = "readonly disable";
		actString = "upbookconfirm";
	}

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>书籍管理</title>
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

						<table width="100%" border="0">
							<tr>
								<td colspan="2" align="center"><div id="title"><%=titleString%></div></td>
							</tr>
                            <tr>
								<td><form  action="BookCAdmin?type=<%=actString%>" method="post"><table width="100%" border="0">
											<tr>
												<td width="33%" align="right">ISBN：</td>
												<td width="33%" align="center"><input name="isbn" type="text" id="textfield" value="<%=bb.getISBN()%>" <%=readonly%>></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">书名：</td>
												<td width="33%" align="center"><input type="text" value="<%=bb.getBNAME()%>" name="bookname" id="textfield">
												</td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">作者：</td>
												<td width="33%" align="center"><input type="text" value="<%=bb.getAUTHER()%>" name="author" id="textfield"></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">出版社：</td>
												<td width="33%" align="center"><input type="text" value="<%=bb.getPRESS()%>" name="press" id="textfield"></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">出版时间：</td>
												<td width="33%" align="center"><input type="text" value="<%=bb.getPTIME()%>" name="time" id="textfield"></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">图书类型：</td>
												<td width="33%" align="center"><input type="text" value="<%=bb.getBTYPE()%>" name="booktype" id="textfield"></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">定价：</td>
												<td width="33%" align="center"><input type="text" value="<%=bb.getPRICE()%>" name="price" id="textfield"></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">借阅权限：</td>
												<td width="33%" align="center"><input type="text" value="<%=bb.getBAUTH()%>" name="auth" id="textfield"></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">图书总量：</td>
												<td width="33%" align="center"><input type="text" value="<%=bb.getSNUM()%>" name="snum" id="textfield"></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">库存数：</td>
												<td width="33%" align="center"><input type="text" value="<%=bb.getBNUM()%>" name="bnum" id="textfield"></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td colspan="3" align="center"><input type="submit" name="button" id="button" value="<%=buttenString%>"></td>
											</tr>
										</table>
										<input type="hidden" name="from" value=""/>
									</form></td></tr>

						</table>

					</div></td>
			<tr>
				<td align="center"><jsp:include flush="true" page="AdminFooter.jsp"/></td>
			</tr>
		</table>

	</body>
</html>
