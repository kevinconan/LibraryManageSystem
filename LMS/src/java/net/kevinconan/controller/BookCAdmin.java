/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kevinconan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.kevinconan.model.BookBO;
import net.kevinconan.model.BookBean;

/**
 * 用于处理关于书籍的增加删除等功能的控制器
 * <p/>
 * @author Diluka
 */
@WebServlet(name = "BookCAdmin", urlPatterns = {"/BookCAdmin"})
public class BookCAdmin extends HttpServlet {

	/**
	 * Processes requests for both HTTP
	 * <code>GET</code> and
	 * <code>POST</code> methods.
	 * <p/>
	 * @param request  servlet request
	 * @param response servlet response
	 * <p/>
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String type = request.getParameter("type");
		String isbn = request.getParameter("isbn");
		if (type.equals("addbook") | type.equals("upbookconfirm")) {
			BookBean bb = new BookBean();

			String bookname = request.getParameter("bookname");
			String author = request.getParameter("author");
			String press = request.getParameter("press");
			String time = request.getParameter("time");
			String booktype = request.getParameter("booktype");
			double price = Double.parseDouble(request.getParameter("price"));
			int auth = Integer.parseInt(request.getParameter("auth"));
			int snum = Integer.parseInt(request.getParameter("snum"));
			int bnum = Integer.parseInt(request.getParameter("bnum"));

			bb.setISBN(isbn);
			bb.setBNAME(bookname);
			bb.setAUTHER(author);
			bb.setPRESS(press);
			bb.setPTIME(time);
			bb.setBTYPE(booktype);
			bb.setPRICE(price);
			bb.setBAUTH(auth);
			bb.setSNUM(snum);
			bb.setBNUM(bnum);

			BookBO bbo = new BookBO();
			if (type.equals("addbook")) {
				if (bbo.addBook(bb)) {

					request.getRequestDispatcher("AdminActRes.jsp?result=成功&from=AdminBooks.jsp&actionType=添加书籍").forward(request, response);
				} else {
					request.getRequestDispatcher("AdminActRes.jsp?result=失败&from=AdminBooks.jsp&actionType=添加书籍").forward(request, response);

				}
			} else if (type.equals("upbookconfirm")) {
				if (bbo.updateBook(bb)) {

					request.getRequestDispatcher("AdminActRes.jsp?result=成功&from=AdminBooks.jsp&actionType=修改书籍").forward(request, response);
				} else {
					request.getRequestDispatcher("AdminActRes.jsp?result=失败&from=AdminBooks.jsp&actionType=修改书籍").forward(request, response);

				}
			}

			//临时跳转
			//request.getRequestDispatcher("test/addbook.jsp").forward(request, response);
		} else if (type.equals("update")) {
			BookBO bbo = new BookBO();
			BookBean bb = bbo.searchBookByISBN(isbn);
			request.setAttribute("bookInfo", bb);
			request.getRequestDispatcher("updateBook.jsp?from=AdminBooks.jsp").forward(request, response);
		} else if (type.equals("del")) {

			BookBO bbo = new BookBO();
			if (bbo.delBookByISBN(isbn)) {
				request.getRequestDispatcher("SearchBook?delok=ok").forward(request, response);
			} else {
				request.getRequestDispatcher("searchresult.jsp?delok=failed").forward(request, response);
			}

		}


	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP
	 * <code>GET</code> method.
	 * <p/>
	 * @param request  servlet request
	 * @param response servlet response
	 * <p/>
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP
	 * <code>POST</code> method.
	 * <p/>
	 * @param request  servlet request
	 * @param response servlet response
	 * <p/>
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 * <p/>
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
