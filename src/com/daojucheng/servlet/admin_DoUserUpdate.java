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
 * Servlet implementation class DoUserUpdate
 */
@WebServlet("/manage/admin_douserupdate")
public class admin_DoUserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin_DoUserUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("userid");
		String name = request.getParameter("username");
		String password = request.getParameter("userpassword");
		String sex = request.getParameter("sex");
		String userStatus =request.getParameter("userStatus");
		String usergameidd =request.getParameter("usergameid");
		int usergameid=Integer.parseInt(usergameidd);
		System.out.println(usergameid);
		int status=1;
		if(userStatus!=null) {
			status=Integer.parseInt(userStatus);
		}
		DAOJUCHENG_USER u=new DAOJUCHENG_USER(id,name,password,sex, status,usergameid);
		int count = DaoJuChengDao.update(u);
		if(count>0) {
			response.sendRedirect("admin_douserselect?cp="+request.getParameter("cpage"));
		}else {
			PrintWriter out=response.getWriter();
			out.write("<script>");
			out.write("alert('用户修改失败!');");
			out.write("location.href='admin_touserupdate?id="+id+"'");
			out.write("</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
