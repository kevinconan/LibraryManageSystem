
<%@page import="net.kevinconan.model.BookCardBean"%>
<%--
    Document   : header
    Created on : 2013-4-8, 17:58:17
    Author     : Diluka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	BookCardBean bcb = (BookCardBean) request.getSession().getAttribute("loginInfo");
%>
<!DOCTYPE html>
<link href="css/maintheme.css" rel="stylesheet" type="text/css">


<table width="100%" border="0">
	<tr>
		<td><div id="headleft"><table width="100%" border="0" class="text">
					<tr>

						<%
							if (bcb != null) {
						%><td><img name="avatar" src="/LibraryManageSystem/images/avatar/<%=bcb.getBAVATAR()%>.gif" width="32" height="32" id="avatar"></td><td><div id="username">您好，<%=bcb.getBNAME()%>！</div></td><%							} else {
						%><td><img name="avatar" src="/LibraryManageSystem/images/avatar/0.gif" width="32" height="32" id="avatar"></td><td>请登录</td><%								}
							%>
                        <td><a href="index.jsp">首页</a></td>
						<td><a href="userinfo.jsp">个人信息</a></td>
						<td><a href="#">借阅管理</a></td>
					</tr>
				</table>
			</div></td>
		<td><div id="headright">
				<%

					if (bcb != null) {
				%><a href="Logout">安全退出</a><%					} else {
				%><a href="Login.jsp">用户登录</a><%						}
				%>

			</div></td>
	</tr>
</table>
