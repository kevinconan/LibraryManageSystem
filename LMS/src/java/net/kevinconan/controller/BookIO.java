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
import net.kevinconan.model.*;

/**
 *
 * @author Kevin
 */
@WebServlet(name = "BookIO", urlPatterns = {"/BookIO"})
public class BookIO extends HttpServlet {

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
		String actionType = request.getParameter("actionType");
		System.out.println(actionType + "test");
		String ISBN = request.getParameter("isbn");
		String BCID = request.getParameter("bcid");
		String ADID = ((AdminBean) request.getSession().getAttribute("AdminLoginInfo")).getADID();
		RecordBO rbo = new RecordBO();
		if (actionType.equals("还书")) {
			if (rbo.inBook(ISBN, BCID, ADID)) {
				request.getRequestDispatcher("AdminActRes.jsp?result=成功&from=AdminIndex.jsp&actionType=还书").forward(request, response);
			} else {
				request.getRequestDispatcher("AdminActRes.jsp?result=失败&from=AdminIndex.jsp&actionType=还书").forward(request, response);
			}
		} else if (actionType.equals("借书")) {
			if (rbo.outBook(ISBN, BCID, ADID)) {
				request.getRequestDispatcher("AdminActRes.jsp?result=成功&from=AdminIndex.jsp&actionType=借书").forward(request, response);
			} else {
				request.getRequestDispatcher("AdminActRes.jsp?result=失败&from=AdminIndex.jsp&actionType=借书").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("AdminActRes.jsp?result=失败&from=AdminIndex.jsp&actionType=未知错误").forward(request, response);
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
