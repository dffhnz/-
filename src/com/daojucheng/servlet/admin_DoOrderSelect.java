package com.daojucheng.servlet;

import com.daojucheng.entity.DAOJUCHENG_SHOPPING;
import com.daojucheng.entity.DAOJUCHENG_USER;
import com.daojucheng.service.DaoJuChengDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/manage/admin_doorderselect")
public class admin_DoOrderSelect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin_DoOrderSelect() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cpage=1;//当前页
        int count=5;//每页显示条数
        //获取用户指定页面
        String cp=request.getParameter("cp");
        if(cp!=null)
            cpage=Integer.parseInt(cp);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        int arr[]= DaoJuChengDao.ordertotalpage(count);
        ArrayList<DAOJUCHENG_SHOPPING> list = DaoJuChengDao.selectorderAll(cpage, count);
        request.setAttribute("orderlist", list);
        request.setAttribute("tsum", arr[0]);
        request.setAttribute("tpage", arr[1]);
        request.setAttribute("cpage", cpage);

        request.getRequestDispatcher("/manage/admin_order.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
