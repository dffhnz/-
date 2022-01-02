package com.daojucheng.servlet;

import com.daojucheng.entity.DAOJUCHENG_CATEGORY;
import com.daojucheng.entity.DAOJUCHENG_ITEM;
import com.daojucheng.service.DaoJuChengDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/item_doitemselect")
public class item_DoItemSelect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public item_DoItemSelect() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cpage=1;//当前页
        int count=8;//每页显示条数
        int categoryy = 0;
        //获取用户指定页面
        String cp=request.getParameter("cp");
        String category=request.getParameter("category");
        if(cp!=null) {
            cpage=Integer.parseInt(cp);
        }
        if(category!=null) {
            categoryy = Integer.parseInt(category);
        }
        //接收用户搜索的关键字
        String keyword=request.getParameter("keywords");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        int arr[]= DaoJuChengDao.itemtotalpage(count, keyword,categoryy);
        ArrayList<DAOJUCHENG_ITEM> list = DaoJuChengDao.selectITEMAll(cpage, count, keyword,categoryy);
        ArrayList<DAOJUCHENG_ITEM> list2 = DaoJuChengDao.selectitemAll();
        ArrayList<DAOJUCHENG_CATEGORY> list1=DaoJuChengDao.selectCategoryAll();
        HttpSession session=request.getSession();
        request.setAttribute("categorylist", list1);
        request.setAttribute("itemlist", list);
        request.setAttribute("itemlist1", list2);
        request.setAttribute("tsum", arr[0]);
        request.setAttribute("tpage", arr[1]);
        request.setAttribute("cpage", cpage);

        if(keyword!=null) {
            request.setAttribute("keywordd", "&keywords="+keyword);
        }
        if(category!=null) {
            request.setAttribute("categoryy", "&category="+categoryy);
        }
//        response.sendRedirect("/index.jsp");
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */


}