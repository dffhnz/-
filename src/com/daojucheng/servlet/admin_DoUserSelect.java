package com.daojucheng.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daojucheng.entity.DAOJUCHENG_CATEGORY;
import com.daojucheng.entity.DAOJUCHENG_USER;
import com.daojucheng.service.DaoJuChengDao;
import com.sun.org.apache.bcel.internal.generic.AALOAD;

/**
 * Servlet implementation class admin_DoUserSelect
 */
@WebServlet("/manage/admin_douserselect")
public class admin_DoUserSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin_DoUserSelect() {
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
		//接收用户搜索的关键字
		String keyword=request.getParameter("keywords");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		int arr[]=DaoJuChengDao.totalpage(count, keyword);
		ArrayList<DAOJUCHENG_USER> list = DaoJuChengDao.selectAll(cpage, count, keyword);
		request.setAttribute("userlist", list);
		request.setAttribute("tsum", arr[0]);
		request.setAttribute("tpage", arr[1]);
		request.setAttribute("cpage", cpage);

		if(keyword!=null) {
			request.setAttribute("keywordd", "&keywords="+keyword);
		}
		request.getRequestDispatcher("admin_user.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	

}