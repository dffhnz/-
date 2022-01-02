package com.daojucheng.servlet;

import com.daojucheng.entity.DAOJUCHENG_CATEGORY;
import com.daojucheng.entity.DAOJUCHENG_USER;
import com.daojucheng.service.DaoJuChengDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/manage/admin_docategoryupdate")
public class admin_DoCategoryUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin_DoCategoryUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String categoryid = request.getParameter("categoryid");
        int id =Integer.parseInt(categoryid);
        String name = request.getParameter("categoryname");
        DAOJUCHENG_CATEGORY c=new DAOJUCHENG_CATEGORY(id,name);
        int count = DaoJuChengDao.categoryupdate(c);
        if(count>0) {
            response.sendRedirect("/manage/admin_docategoryselect");
        }else {
            PrintWriter out=response.getWriter();
            out.write("<script>");
            out.write("alert('用户修改失败!');");
            out.write("location.href='admin_tocategoryupdate?id="+id+"'");
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
