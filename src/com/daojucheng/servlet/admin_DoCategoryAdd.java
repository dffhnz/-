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

@WebServlet("/manage/admin_docategoryadd")
public class admin_DoCategoryAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin_DoCategoryAdd() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String name = request.getParameter("categoryName");
        DAOJUCHENG_CATEGORY c=new DAOJUCHENG_CATEGORY(0, name);

        int count = DaoJuChengDao.categoryadd(c);
        if(count>0) {
            response.sendRedirect("/manage/admin_docategoryselect");
        }else {
            PrintWriter out=response.getWriter();
            out.write("<script>");
            out.write("alert('用户添加失败')");
            out.write("location.href='manage/admin_cateadd.jsp'");
            out.write("</script>");
        }
    }
}
