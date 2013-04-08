/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.kevinconan.controller;
import javax.servlet.http.*;
import java.io.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *登陆界面
 * @author Kevin
 */
public class Logintest extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse res){
        try{
            res.setContentType("text/html;charset=gbk");
            PrintWriter pw=res.getWriter();
            String info=request.getParameter("info");
            
            pw.println("<html>");
            pw.println("<body bgcolor=#D4D8FF><center>");
            pw.println("<img src=imgs/logo1.png><hr>");
            pw.println("<h1>用户登陆</h1>");
            if(info!=null){
                pw.println("你的用户名或者密码错误<br>");
            }
            pw.println("<form action=AdminLoginChk method=post>");
            pw.println("用户名:<input type=text name=username><br>");
            pw.println("密码:<input type=text name=password><br>");
            pw.println("<input type=checkbox name=keep value=2>两周内不再重新登陆<br>");
            pw.println("<input type=submit value=login>");
            pw.println("</form>");
             pw.println("<br><br><hr><img src=imgs/logo2.png>");
            pw.println("</center></body>");
            pw.println("</html>");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
