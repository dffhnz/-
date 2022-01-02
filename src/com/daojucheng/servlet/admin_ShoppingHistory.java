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

@WebServlet("/manage/admin_shoppinghistory")
public class admin_ShoppingHistory extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin_ShoppingHistory() {
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
        int cpage=1;//当前页
        int count=5;//每页显示条数
        //获取用户指定页面
        String cp=request.getParameter("cp");
        if(cp!=null)
            cpage=Integer.parseInt(cp);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        int arr[]= DaoJuChengDao.historytotalpage(count);
        ArrayList<DAOJUCHENG_SHOPPING> list = DaoJuChengDao.shoppinghistorytotalpage(cpage, count);
        request.setAttribute("historylist", list);
        request.setAttribute("tsum", arr[0]);
        request.setAttribute("tpage", arr[1]);
        request.setAttribute("cpage", cpage);
        request.getRequestDispatcher("/manage/admin_shoppinghistory.jsp").forward(request, response);
    }
}
