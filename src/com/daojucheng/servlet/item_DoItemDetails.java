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
import java.util.ArrayList;
@WebServlet("/item_doitemdetails")
public class item_DoItemDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public item_DoItemDetails() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int iid=0;
        //获取用户指定页面
        String id=request.getParameter("id");
        if(id!=null)
            iid=Integer.parseInt(id);
        //接收用户搜索的关键字
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session=request.getSession();
        DAOJUCHENG_ITEM i=DaoJuChengDao.selectItemId(iid);
        ArrayList<DAOJUCHENG_CATEGORY> list1=DaoJuChengDao.selectCategoryAll();
        ArrayList<DAOJUCHENG_ITEM> list2 = DaoJuChengDao.selectitemAll();
        request.setAttribute("itemlist1", list2);
        request.setAttribute("itemlist", i);
        request.setAttribute("categorylist", list1);
        request.getRequestDispatcher("/item_details.jsp").forward(request,response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */


}
