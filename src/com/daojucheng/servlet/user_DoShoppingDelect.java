package com.daojucheng.servlet;

import com.daojucheng.service.DaoJuChengDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/doshoppingdelect")
public class user_DoShoppingDelect extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");
        int count = DaoJuChengDao.shoppingdelete(Integer.parseInt(id));
        if(count>0){
            response.sendRedirect("user_doshoppingselect");
        }else{
            PrintWriter out =response.getWriter();
            out.write("<script>");
            out.write("alert('商品删除失败！');");
            out.write("location.href='user_doshoppingselect'");
            out.write("</script>");
        }

    }
}