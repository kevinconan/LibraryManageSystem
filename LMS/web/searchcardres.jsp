<%--
    Document   : searchresult
    Created on : 2013-4-8, 22:42:39
    Author     : Diluka
--%>

<%@page import="net.kevinconan.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String identity;
	if (request.getSession().getAttribute("AdminLoginInfo") != null) {
		identity = "admin";
	} else {
		identity = "user";
	}
	ArrayList<BookCardBean> al = (ArrayList<BookCardBean>) request.getAttribute("searchresult");
	int pageCount = Integer.parseInt((String) request.getAttribute("pageCount"));
	int pageNow = Integer.parseInt(request.getParameter("pageNow"));
	String searchBookCard = request.getParameter("searchBookCard");
%>
<script type="text/javascript">
	function delcard() {
		window.document.getElementsByName("actionType").value = "out";
		return window.confirm("注销这张借书卡吗？");
	}


</script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=searchBookCard%> - 搜索结果</title>
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
							<th>借书卡ID</th>

							<th>姓名</th>
							<th>性别</th>
							<th>借书权限</th>
							<th>办理日期</th>
							<th>用户头像</th>

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
									for (BookCardBean bcb : al) {
						%><tr>
							<td><%=bcb.getBCID()%></td>
							<td><%=bcb.getBNAME()%></td>
							<td><%=bcb.getBSEX()%></td>
							<td><%=bcb.getBAUTH()%></td>
							<td><%=bcb.getBDATE()%></td>
							<td><img name="avatar" src="/LibraryManageSystem/images/avatar/<%=bcb.getBAVATAR()%>.gif" width="32" height="32" id="avatar"></td>

							<%
								if (identity.equals("admin")) {
							%> <td><form method="post" action="BookCardCAdmin">
									<input type="hidden" name="bcid" value="<%=bcb.getBCID()%>">
									<input type="hidden" name="type" value="update">
									<input type="submit" name="button" id="button" value="修改" >
								</form><%
							%><td><form method="post" action="BookCardCAdmin">
									<input type="hidden" name="bcid" value="<%=bcb.getBCID()%>">
									<input type="hidden" name="type" value="del">
									<input type="hidden" name="searchBookCard" value="<%=searchBookCard%>">
									<input type="hidden" name="pageNow" value="<%=pageNow%>">
									<input type="hidden" name="pageCount" value="<%=pageCount%>">
									<input type="submit" name="button" id="button" value="注销" onClick="return delcard();"> </form><%
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
								%><a href="SearchCard?searchBookCard=<%=searchBookCard%>&pageNow=1">首页</a><%
								%>&nbsp;<%
								%><a href="SearchCard?searchBookCard=<%=searchBookCard%>&pageNow=<%=pageNow - 1%>">上一页</a><%
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
								%><a href="SearchCard?searchBookCard=<%=searchBookCard%>&pageNow=<%=i%>">[<%=i%>]</a><%
								} else {
								%>><%=i%><<%
									}
								%>&nbsp;<%
									}
									if (pageNow != pageCount & pageCount != 0) {
								%><a href="SearchCard?searchBookCard=<%=searchBookCard%>&pageNow=<%=pageNow + 1%>">下一页</a><%
								%>&nbsp;<%
								%><a href="SearchCard?searchBookCard=<%=searchBookCard%>&pageNow=<%=pageCount%>">末页</a><%
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
