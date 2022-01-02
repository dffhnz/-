package com.daojucheng.servlet;

import com.daojucheng.entity.DAOJUCHENG_SHOPPING;
import com.daojucheng.entity.DAOJUCHENG_USER;
import com.daojucheng.service.DaoJuChengDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/user_doshoppingselect")
public class user_DoShoppingSelect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public user_DoShoppingSelect() {
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
        HttpSession session=request.getSession();
        DAOJUCHENG_USER u = (DAOJUCHENG_USER) session.getAttribute("user_list");
        int islogin = (int) session.getAttribute("islogin");
        if(islogin==1) {
            String user_id = u.getUser_id();
            ArrayList<DAOJUCHENG_SHOPPING> list = DaoJuChengDao.shoppingselect(user_id);
            request.setAttribute("slist", list);
            request.getRequestDispatcher("/shopping.jsp").forward(request, response);
        }else {
            response.sendRedirect("/shopping.jsp");
        }
    }
}
