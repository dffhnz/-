package com.daojucheng.servlet;

import com.daojucheng.entity.DAOJUCHENG_CATEGORY;
import com.daojucheng.entity.DAOJUCHENG_ITEM;
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
import java.util.ArrayList;


@WebServlet("/user_login")
public class user_login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public user_login() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("user_name");
        String password = request.getParameter("user_password");
        // String sex = request.getParameter("sex");
        DAOJUCHENG_USER u=DaoJuChengDao.selectByID(id);
        int count = DaoJuChengDao.selectByNamePassWord(id,password);
        if(count>0){
            HttpSession session=request.getSession();
            session.setAttribute("user_list",u);
            session.setAttribute("islogin",1);
            response.sendRedirect("item_doitemselect");
        }else{
            PrintWriter out=response.getWriter();
            out.write("<script>");
            out.write("alert('用户登录失败！');");
            out.write("location.href='/enroll.jsp';");
            out.write("</script>");
        }
    }
}

