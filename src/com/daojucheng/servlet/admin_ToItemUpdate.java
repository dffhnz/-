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
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/manage/admin_toitemupdate")
public class admin_ToItemUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin_ToItemUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");
        //通过id从数据库里查找
        DAOJUCHENG_ITEM item = DaoJuChengDao.selectByitemID(id);
        ArrayList<DAOJUCHENG_CATEGORY> list =DaoJuChengDao.selectCategoryAll();
        request.setAttribute("itemlist", item);
        request.setAttribute("clist", list);
        request.setAttribute("cpage", request.getParameter("cpage"));
        request.getRequestDispatcher("/manage/admin_itemmodify.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
