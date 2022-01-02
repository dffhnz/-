package com.daojucheng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daojucheng.entity.DAOJUCHENG_USER;
import com.daojucheng.service.DaoJuChengDao;

/**
 * Servlet implementation class admin_useradd
 */
@WebServlet("/manage/admin_douseradd")
public class admin_DoUserAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public admin_DoUserAdd() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String id = request.getParameter("userId");
		String name = request.getParameter("userName");
		String password = request.getParameter("passWord");
		String sex = request.getParameter("Sex");

		DAOJUCHENG_USER u=new DAOJUCHENG_USER( id, name, password, sex, 1,0);

		int count = DaoJuChengDao.insert(u);
		if(count>0) {
			response.sendRedirect("/manage/admin_douserselect");
		}else {
			PrintWriter out=response.getWriter();
			out.write("<script>");
			out.write("alert('用户添加失败')");
			out.write("location.href='/manage/admin_useradd.jsp'");
			out.write("</script>");
		}
	}

}
