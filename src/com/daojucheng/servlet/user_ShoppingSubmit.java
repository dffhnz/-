package com.daojucheng.servlet;

import com.daojucheng.entity.DAOJUCHENG_SHOPPING;
import com.daojucheng.service.DaoJuChengDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user_shoppingsubmit")
public class user_ShoppingSubmit extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String ids[] =request.getParameterValues("id[]");
        for (int j = 0; j < ids.length; j++) {
            DAOJUCHENG_SHOPPING s=DaoJuChengDao.shoppingByitemID(Integer.parseInt(ids[j]));
            int count = DaoJuChengDao.shoppingsubmit(s);
        }
        response.sendRedirect("/user_doshoppingselect");
    }
}
