package com.daojucheng.servlet;

import com.daojucheng.entity.DAOJUCHENG_USER;
import com.daojucheng.service.DaoJuChengDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/manage/user_register")
public class user_register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("userName");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        DAOJUCHENG_USER u=new DAOJUCHENG_USER(id,name,password,sex,1,0);
        int count = DaoJuChengDao.insert(u);
        if(count>0){
            HttpSession session=request.getSession();
            session.setAttribute("user_list",u);
            session.setAttribute("islogin",1);
            response.sendRedirect("/item_doitemselect");
        }else{
            PrintWriter out=response.getWriter();
            out.write("<script>");
            out.write("alert('用户注册失败！');");
            out.write("location.href='/enroll.jsp';");
            out.write("</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}

