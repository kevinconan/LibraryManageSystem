<%--
    Document   : searchresult
    Created on : 2013-4-8, 22:42:39
    Author     : Diluka
--%>

<%@page import="net.kevinconan.model.BookBean"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String identity;
	if (request.getSession().getAttribute("AdminLoginInfo") != null) {
		identity = "admin";
	} else {
		identity = "user";
	}
	ArrayList<BookBean> al = (ArrayList<BookBean>) request.getAttribute("searchresult");
	int pageCount = Integer.parseInt((String) request.getAttribute("pageCount"));
	int pageNow = Integer.parseInt(request.getParameter("pageNow"));
	String searchText = request.getParameter("searchText");
%>
<script type="text/javascript">
	function delbook() {
		window.document.getElementsByName("actionType").value = "out";
		return window.confirm("确认删除这本书吗？");
	}


</script>
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
				<%
					if (identity.equals("admin")) {
				%><td><jsp:include flush="true" page="AdminHeader.jsp"/></td><%						} else {
				%><td><jsp:include flush="true" page="header.jsp"/></td><%							}
				%>
			</tr>
			<tr>
				<td align="center"><table width="100%" border="1" class="text">
						<tr>
							<th>ISBN</th>
							<th>书名</th>
							<th>作者</th>
							<th>出版社</th>
							<th>出版时间</th>
							<th>类型</th>
							<th>定价</th>
							<th>借阅权限</th>
							<th>库存</th>
								<%
									if (identity.equals("admin")) {
								%><th>修改</th><%
							%><th>删除</th><%									}
								%>
						</tr>
						<%
							//System.out.println(al);
							if (al != null)
								if (!al.isEmpty()) {
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
							<%
								if (identity.equals("admin")) {
							%> <td><form method="post" action="BookCAdmin">
									<input type="hidden" name="isbn" value="<%=bb.getISBN()%>">
									<input type="hidden" name="type" value="update">
									<input type="submit" name="button" id="button" value="修改" >
								</form><%
							%><td><form method="post" action="BookCAdmin">
									<input type="hidden" name="isbn" value="<%=bb.getISBN()%>">
									<input type="hidden" name="type" value="del">
									<input type="hidden" name="searchText" value="<%=searchText%>">
									<input type="hidden" name="pageNow" value="<%=pageNow%>">
									<input type="hidden" name="pageCount" value="<%=pageCount%>">
									<input type="submit" name="button" id="button" value="删除" onClick="return delbook();"> </form><%
										}
									%></tr>
							<%
								}
							} else {
							%><tr><td colspan="9" align="center">通过努力的搜索，但是还是没有找到结果= =||</td></tr><%									}

						%>

						<tr>
							<td colspan="9" align="center"><%
								if (pageNow != 1) {
								%><a href="SearchBook?searchText=<%=searchText%>&pageNow=1">首页</a><%
								%>&nbsp;<%
								%><a href="SearchBook?searchText=<%=searchText%>&pageNow=<%=pageNow - 1%>">上一页</a><%
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
								%><a href="SearchBook?searchText=<%=searchText%>&pageNow=<%=i%>">[<%=i%>]</a><%
								} else {
								%>><%=i%><<%
									}
								%>&nbsp;<%
									}
									if (pageNow != pageCount & pageCount != 0) {
								%><a href="SearchBook?searchText=<%=searchText%>&pageNow=<%=pageNow + 1%>">下一页</a><%
								%>&nbsp;<%
								%><a href="SearchBook?searchText=<%=searchText%>&pageNow=<%=pageCount%>">末页</a><%
									}
								%></td>
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
