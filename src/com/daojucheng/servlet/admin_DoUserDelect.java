package com.daojucheng.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daojucheng.service.DaoJuChengDao;

/**
 * Servlet implementation class admin_DoUserDelect
 */
@WebServlet("/manage/admin_douserdelect")
public class admin_DoUserDelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin_DoUserDelect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String id = request.getParameter("id");
		String cp = request.getParameter("cpage");
		if(cp==null){
			cp="1";
		}
		int count = DaoJuChengDao.delete(id);
		if(count>0) {
			response.sendRedirect("admin_douserselect");
		}else {
			PrintWriter out=response.getWriter();
			out.write("<script>");
			out.write("alert('用户删除失败!');");
			out.write("location.href='admin_douserselect?cp="+cp+"'");
			out.write("</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String ids[] =request.getParameterValues("id[]");
		for (int j = 0; j < ids.length; j++) {
			int count = DaoJuChengDao.delete(ids[j]);
		}
		response.sendRedirect("admin_douserselect");
	}

}
