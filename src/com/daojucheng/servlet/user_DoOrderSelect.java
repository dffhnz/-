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
@WebServlet("/user_doorderselect")
public class user_DoOrderSelect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public user_DoOrderSelect() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session=request.getSession();
        DAOJUCHENG_USER u = (DAOJUCHENG_USER) session.getAttribute("user_list");
        ArrayList<DAOJUCHENG_SHOPPING> list = DaoJuChengDao.userselectorderAll(u.getUser_id());
        request.setAttribute("orderlist", list);
        request.getRequestDispatcher("/user_order.jsp").forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
