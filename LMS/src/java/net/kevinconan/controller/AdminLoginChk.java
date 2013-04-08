/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kevinconan.controller;

import net.kevinconan.model.AdminBO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Kevin
 */
public class AdminLoginChk extends HttpServlet {
    
    public void doGet(HttpServletRequest req, HttpServletResponse res){
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        AdminBO abw=new AdminBO();
      //  abw.createAdmin(username, password);  //只是为了 测试问题多得很
        if(abw.checkAdmin(username, password)){
            HttpSession hs=req.getSession(true);
                    hs.setMaxInactiveInterval(30);
                    hs.setAttribute("adminID",username);
                    try {
                        res.sendRedirect("AdminMain.jsp");
                
                    } catch (Exception e) {
                     e.printStackTrace();
                    }
        }else{
            try {
                res.sendRedirect("AdminLogin.jsp?info=err1");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res){
        this.doGet(req, res);
    }
    
}

  

   