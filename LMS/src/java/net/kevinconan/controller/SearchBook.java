/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kevinconan.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.kevinconan.model.BookBO;
import net.kevinconan.model.BookBean;

/**
 *
 * @author Diluka
 */
@WebServlet(name = "SearchBook", urlPatterns = {"/SearchBook"})
public class SearchBook extends HttpServlet {

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
		request.setCharacterEncoding("utf-8");//设置接收编码格式
		response.setContentType("text/html;charset=UTF-8");
		int pageSize = 10;
		int pageNow = 1;
		String searchString = request.getParameter("searchText");
		String pageNowString = request.getParameter("pageNow");
		String identity = request.getParameter("admin");
		if (identity == null) {
			identity = "user";
		}
		if (pageNowString != null) {
			pageNow = Integer.parseInt(pageNowString);
		}
		System.out.println(pageNow);
		BookBO bbo = new BookBO();
		bbo.setSearchPattern(searchString);
		int resultCount = bbo.getSearchCount();
		int pageCount = (int) Math.ceil(1.0 * resultCount / pageSize);
		request.setAttribute("pageCount", pageCount + "");

		ArrayList<BookBean> al = bbo.searchBook(pageNow, pageSize);
		request.setAttribute("searchresult", al);
		System.out.println(al);
		if (identity.equals("admin")) {
			//request.getRequestDispatcher("")
		} else {
			request.getRequestDispatcher("searchresult.jsp?searchText=" + searchString + "&pageNow=" + pageNow).forward(request, response);
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
