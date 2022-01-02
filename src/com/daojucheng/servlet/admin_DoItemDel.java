package com.daojucheng.servlet;

import com.daojucheng.service.DaoJuChengDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/manage/admin_doitemdel")
public class admin_DoItemDel extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin_DoItemDel() {
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
        int count = DaoJuChengDao.itemdelete(id);
        if(count>0) {
            response.sendRedirect("admin_doitemselect?cp="+cp);
        }else {
            PrintWriter out=response.getWriter();
            out.write("<script>");
            out.write("alert('用户删除失败!');");
            out.write("location.href='admin_doitemselect?cp="+cp+"'");
            out.write("</script>");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
