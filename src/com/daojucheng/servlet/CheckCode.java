package com.daojucheng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/checkCode")
public class CheckCode extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String name=request.getParameter("num");
        HttpSession session=request.getSession();
        // 验证验证码
        String sessionCode = (String)session.getAttribute("code");
        PrintWriter out= response.getWriter();
        if (sessionCode.equals(name)) {
            	out.print("turn");
            } else {
            	out.print("false");
            }
        }
 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		

}
}
