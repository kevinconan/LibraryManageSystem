<%--
    Document   : bookdetail
    Created on : 2013-4-9, 16:28:24
    Author     : Diluka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>书籍详情</title>
		<link href="css/maintheme.css" rel="stylesheet" type="text/css">
    </head>
    <body>
		<table width="95%" border="0" align="center">
			<tr>
				<td align="center"><jsp:include flush="true" page="header.jsp"/></td>
			</tr>
			<tr>
				<td align="center"><div id="infobox">
						<table width="95%" border="0">
							<tr>
								<th colspan="2">BookTitle</th>
							</tr>
							<tr>
								<td width="10%" rowspan="7"><div id="cover"><img name="cover" src="" width="240" height="320" alt=""></div></td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td>ISBN: isbn</td>
							</tr>
							<tr>
								<td>作者：author</td>
							</tr>
							<tr>
								<td>出版社：press</td>
							</tr>
							<tr>
								<td>出版时间：time</td>
							</tr>
							<tr>
								<td>库存：bnum</td>
							</tr>
							<tr>
								<td align="left" valign="top"><p>书籍介绍：</p>
							    <p>intro</p></td>
							</tr>
							<tr>
								<td colspan="2" align="center">书籍分类：type</td>
							</tr>
							<tr>
								<td colspan="2"><input type="button" name="button" id="button" value="按钮">
									<input type="button" name="button2" id="button2" value="按钮"></td>
							</tr>
						</table>
			  </div></td>
			</tr>
			<tr>
				<td align="center"><jsp:include flush="true" page="footer.jsp"/></td>
			</tr>
		</table>

    </body>
</html>
