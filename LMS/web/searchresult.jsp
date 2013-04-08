<%--
    Document   : searchresult
    Created on : 2013-4-8, 22:42:39
    Author     : Diluka
--%>

<%@page import="net.kevinconan.model.BookBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	ArrayList<BookBean> al = (ArrayList<BookBean>) request.getAttribute("searchresult");
	int pageCount = Integer.parseInt((String) request.getAttribute("pageCount"));
	int pageNow = Integer.parseInt(request.getParameter("pageNow"));
	String searchText = request.getParameter("searchText");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=searchText%> - 搜索结果</title>
		<link href="css/maintheme.css" rel="stylesheet" type="text/css">
    </head>
    <body>
		<table width="95%" border="0" align="center">
			<tr>
				<td align="center"><jsp:include flush="true" page="header.jsp"/></td>
			</tr>
			<tr>
				<td align="center"><table width="100%" border="1">
						<tr>
							<td>ISBN</td>
							<td>书名</td>
							<td>作者</td>
							<td>出版社</td>
							<td>出版时间</td>
							<td>类型</td>
							<td>定价</td>
							<td>借阅权限</td>
							<td>库存</td>
						</tr>
						<%
							if (al != null)
								for (BookBean bb : al) {
						%><tr>
							<td><%=bb.getISBN()%></td>
							<td><%=bb.getBNAME()%></td>
							<td><%=bb.getAUTHER()%></td>
							<td><%=bb.getPRESS()%></td>
							<td><%=bb.getPTIME()%></td>
							<td><%=bb.getBTYPE()%></td>
							<td><%=bb.getPRICE()%></td>
							<td><%=bb.getBAUTH()%></td>
							<td><%=bb.getBNUM()%></td>
						</tr><%
								}

						%>

						<tr>
							<td colspan="9" align="center"><%
								if (pageNow != 1) {
								%><a href="SearchBook?pageNow=<%=pageNow - 1%>">上一页</a><%
									}
									for (int i = pageNow; i < pageNow + 5; i++) {
										if (i == pageCount) {
											break;
										}
								%><a href="SearchBook?pageNow=<%=i%>">【<%=i%>】</a><%
									}
									if (pageNow != pageCount) {
								%><a href="SearchBook?pageNow=<%=pageNow + 1%>">下一页</a><%
									}
								%></td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td align="center"><jsp:include flush="true" page="footer.jsp"/></td>
			</tr>
		</table>

    </body>
</html>
