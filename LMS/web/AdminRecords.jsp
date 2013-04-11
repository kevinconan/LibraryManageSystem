<%--
    Document   : AdminRecords
    Created on : 2013-4-11, 8:50:23
    Author     : Kevin
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="net.kevinconan.model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

	ArrayList<RecordBean> al = (ArrayList<RecordBean>) request.getAttribute("recordList");
	int pageCount = 1;
	String pageCountString = (String) request.getParameter("pageCount");
	if (pageCountString != null) {
		pageCount = Integer.parseInt(pageCountString);
	}
	int pageNow = 1;
	String pageNowString = request.getParameter("pageNow");
	if (pageNowString != null) {
		pageNow = Integer.parseInt(pageNowString);
	}
	String viewType = request.getParameter("viewType");

	if (viewType == null) {
		viewType = "all";
	} else if (!(viewType.equals("in") | viewType.equals("out"))) {
		viewType = "all";
	}
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>借阅管理</title>
		<link href="css/maintheme.css" rel="stylesheet" type="text/css">
    </head>
    <body>
		<table width="95%" border="0" align="center">
			<tr>
				<td align="center"><jsp:include flush="true" page="AdminHeader.jsp"/></td>
			</tr>
			<tr>
				<td align="center"><div id="infobox"><table width="100%" border="0">
							<tr>
								<td align="right"><div id="title">查看借阅记录</div></td>
							</tr>
							<tr>
								<td><div id="menu">
										<table width="100%" border="0">
											<tr>
												<td width="70%" align="right">&nbsp;</td>
												<td width="10%" align="right"><%if (viewType.equals("all")) {
													%>>全部借阅<<%												} else {
													%><a href="RecordCAdmin?viewType=all">全部借阅</a><%														}%></td>
											  <td width="10%" align="right"><%if (viewType.equals("in")) {
													%>>已归还<<%												} else {
													%><a href="RecordCAdmin?viewType=in">已归还</a><%														}%></td>
											  <td width="10%" align="right"><%if (viewType.equals("out")) {
													%>>未归还<<%												} else {
													%><a href="RecordCAdmin?viewType=out">未归还</a><%														}%></td>
											</tr>
										</table>

									</div></td>
							</tr>
							<tr>
								<td><table width="100%" border="1">
										<tr>
											<th>SN</th>
											<th>BCID</th>
											<th>ISBN</th>
											<th>书名</th>
											<th>借出时间</th>
											<th>到期时间</th>
											<th>归还时间</th>
										</tr>
										<%
											//System.out.println(al);
											if (al != null)
												if (!al.isEmpty()) {
													for (RecordBean rb : al) {
														Date EDate = new Date(rb.getODATE().getTime() + 30 * 24 * 3600 * 1000L);
														//System.out.println(rb.getBCID());

										%>
										<tr>
											<th><%=rb.getSN()%></th>
											<td><%=rb.getBCID()%></td>
											<td><%=rb.getISBN()%></td>
											<td><%=rb.getBNAME()%></td>
											<td><%=rb.getODATE()%></td>
											<td><%=EDate%></td>
											<%
												if (rb.getRDATE() != null) {
											%>
											<td><%=rb.getRDATE()%></td>
											<%
											} else {
											%><td>未归还</td><%													}
											%>

										</tr>
										<%											}
										} else {
										%>没有记录<%													}
										%>
										<tr>
											<td colspan="7" align="center"><%
												if (pageNow != 1) {
												%><a href="RecordCAdmin?viewType=<%=viewType%>&pageNow=1">首页</a><%
												%>&nbsp;<%
												%><a href="RecordCAdmin?viewType=<%=viewType%>&pageNow=<%=pageNow - 1%>">上一页</a><%
												%>&nbsp;<%
													}
													for (int i = pageNow - 2; i <= pageNow + 2; i++) {
														if (i <= 0) {
															continue;
														}
														if (i > pageCount) {
															break;
														}
														if (i != pageNow) {
												%><a href="RecordCAdmin?viewType=<%=viewType%>&pageNow=<%=i%>">[<%=i%>]</a><%
												} else {
												%>><%=i%><<%
													}
												%>&nbsp;<%
													}
													if (pageNow != pageCount & pageCount != 0) {
												%><a href="RecordCAdmin?viewType=<%=viewType%>&pageNow=<%=pageNow + 1%>">下一页</a><%
												%>&nbsp;<%
												%><a href="RecordCAdmin?viewType=<%=viewType%>&pageNow=<%=pageCount%>">末页</a><%
													}
												%></td>
										</tr>

									</table></td>
							</tr>
						</table></div></td>
			</tr>
			<tr>
				<td align="center"><jsp:include flush="true" page="AdminFooter.jsp"/></td>
			</tr>
		</table>
    </body>
</html>
