package com.daojucheng.servlet;

import com.daojucheng.entity.DAOJUCHENG_SHOPPING;
import com.daojucheng.service.DaoJuChengDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user_shoppinggoods")
public class user_ShoppingGoods extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String id =request.getParameter("id");
        DAOJUCHENG_SHOPPING s= DaoJuChengDao.shoppingByitemID(Integer.parseInt(id));
        int count = DaoJuChengDao.shoppinggoods(s);
        response.sendRedirect("/user_doorderselect");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request,response);
    }
}
