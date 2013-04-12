<%--
    Document   : addcard
    Created on : 2013-4-11, 18:37:06
    Author     : Kevin
--%>

<%@page import="java.sql.Date"%>
<%@page import="net.kevinconan.model.BookCardBean"%>
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
	String dateString = "";
	BookCardBean bcb = new BookCardBean(true);
	if (actionType.equals("add")) {

		titleString = "添加借书卡";
		buttenString = "添加";
		actString = "addcard";
		Date bdate = new Date(new java.util.Date().getTime());
		dateString = bdate.toString();
	} else if (actionType.equals("update")) {
		bcb = (BookCardBean) request.getAttribute("cardInfo");
		titleString = "修改借书卡";
		buttenString = "修改";
		readonly = "readonly disable";
		actString = "upcardconfirm";
		dateString = bcb.getBDATE();
	}

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>借书卡管理</title>
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
								<td><form  action="BookCardCAdmin?type=<%=actString%>" method="post"><table width="100%" border="0">
											<tr>
												<td width="33%" align="right">借书卡ID：</td>
												<td width="33%" align="center"><input name="bcid" type="text" id="textfield" value="<%=bcb.getBCID()%>" <%=readonly%>></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">密码：</td>
												<td width="33%" align="center"><input type="password" value="" name="bpasswd" id="textfield">
												</td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">姓名：</td>
												<td width="33%" align="center"><input type="text" value="<%=bcb.getBNAME()%>" name="bname" id="textfield"></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">性别：</td>
												<td width="33%" align="center"><input type="text" value="<%=bcb.getBSEX()%>" name="bsex" id="textfield"></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">借书权限：</td>
												<td width="33%" align="center"><input type="text" value="<%=bcb.getBAUTH()%>" name="bauth" id="textfield"></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">办理日期：</td>
												<td width="33%" align="center"><input type="text" value="<%=dateString%>" name="bdate" id="textfield" readonly disable></td>
												<td width="33%">&nbsp;</td>
											</tr>
											<tr>
												<td width="33%" align="right">用户头像：</td>

												<td width="33%" align="center">
													选择头像：
													<SELECT onChange="document.bavatar.src = 'images/avatar/' + options[selectedIndex].value + '.gif'"
															size="1" name="bavatar">
														<%
															for (int i = 10; i <= 92; i++) {
														%><option value="<%=i%>"<%
															if (String.valueOf(i).equals(bcb.getBAVATAR())) {
																%>selected="selected"<%																}
																%>>头像<%=i%></option><%
																	}
														%>
													</SELECT>

													<img src="images/avatar/<%=bcb.getBAVATAR()%>.gif" name="bavatar" width="32" height="32">
												</td>
												<td width="33%" align="center"></td>
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
