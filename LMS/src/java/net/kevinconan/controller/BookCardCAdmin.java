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
 * 借书卡管理servlet
 * <p/>
 * @author Kevin
 */
@WebServlet(name = "BookCardCAdmin", urlPatterns = {"/BookCardCAdmin"})
public class BookCardCAdmin extends HttpServlet {

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
		String bcid = request.getParameter("bcid");
		if (type.equals("addcard") | type.equals("upcardconfirm")) {
			String bpasswd = request.getParameter("bpasswd");
			String bname = request.getParameter("bname");
			String bsex = request.getParameter("bsex");
			int bauth = Integer.parseInt(request.getParameter("bauth"));
			String bdate = request.getParameter("bdate");
			String bavatar = request.getParameter("bavatar");
			BookCardBO bcbo = new BookCardBO();
			BookCardBean bcb = new BookCardBean();
			bcb.setBCID(bcid);
			bcb.setBPASSWD(bpasswd);

			bcb.setBNAME(bname);
			bcb.setBSEX(bsex);
			bcb.setBAUTH(bauth);
			bcb.setBDATE(bdate);
			bcb.setBAVATAR(bavatar);

			if (type.equals("addcard")) {
				if (bcbo.addBookCard(bcb)) {

					request.getRequestDispatcher("AdminActRes.jsp?result=成功&from=AdminBookCards.jsp&actionType=注册借书卡").forward(request, response);
				} else {
					request.getRequestDispatcher("AdminActRes.jsp?result=失败&from=AdminBookCards.jsp&actionType=注册借书卡").forward(request, response);

				}
			} else if (type.equals("upcardconfirm")) {
				System.out.println(bcb.getBNAME());
				if (bcbo.updateBookCard(bcb)) {

					request.getRequestDispatcher("AdminActRes.jsp?result=成功&from=AdminBookCards.jsp&actionType=修改借书卡").forward(request, response);
				} else {
					request.getRequestDispatcher("AdminActRes.jsp?result=失败&from=AdminBookCards.jsp&actionType=修改借书卡").forward(request, response);

				}
			}
		} else if (type.equals("update")) {
			BookCardBO bcbo = new BookCardBO();
			BookCardBean bcb = bcbo.showBookCard(bcid);
			System.out.println(bcid);
			request.setAttribute("cardInfo", bcb);
			request.getRequestDispatcher("updateCard.jsp?from=AdminBookCards.jsp").forward(request, response);
		} else if (type.equals("del")) {

			BookCardBO bcbo = new BookCardBO();
			if (bcbo.delBookCard(bcid)) {
				request.getRequestDispatcher("SearchCard?delok=ok").forward(request, response);
			} else {
				request.getRequestDispatcher("AdminActRes.jsp?result=失败&from=AdminBookCards.jsp&actionType=删除借书卡").forward(request, response);
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
