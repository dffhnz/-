package com.daojucheng.servlet;

import com.daojucheng.entity.DAOJUCHENG_USER;
import com.daojucheng.service.DaoJuChengDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UserUpdatePassWord")
public class user_UserPassWordUpdate extends HttpServlet {
    public user_UserPassWordUpdate() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session=request.getSession();
        DAOJUCHENG_USER u1= (DAOJUCHENG_USER) session.getAttribute("user_list");
        String name = u1.getUser_name();
        String password = request.getParameter("user_password");
        DAOJUCHENG_USER u=new DAOJUCHENG_USER(u1.getUser_id(),name,password,u1.getUser_sex(),u1.getUser_status(),u1.getUser_gameid());
        int count= DaoJuChengDao.userupdatepassword(u);
        session.setAttribute("user_list",u);
        if (count>0) {
            request.getRequestDispatcher("/UserUW.jsp").forward(request,response);
        }else {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alter('修改信息失败！')");
            out.write("location.href='/UserUW.jsp'");
            out.write("</script>");
        }
    }
}
