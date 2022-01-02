package com.daojucheng.servlet;

import com.daojucheng.entity.DAOJUCHENG_ITEM;
import com.daojucheng.entity.DAOJUCHENG_USER;
import com.daojucheng.service.DaoJuChengDao;
import com.jspsmart.upload.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@WebServlet("/manage/admin_doitemadd")
public class admin_DoItemAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin_DoItemAdd() {
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
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //创建SmartUpload对象
        SmartUpload su=new SmartUpload();

        //初始化
        su.initialize(this.getServletConfig(),request,response);
        try {
            su.upload();
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
        //获取上传文件
        Files fs=su.getFiles();
        File f=fs.getFile(0);
        //获取的上传文件名称
        String image = f.getFileName();
        try {
            f.saveAs("C:/Users/杨某人/Desktop/DaoJuCheng/web/image/"+image,File.SAVEAS_PHYSICAL);
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
        Request req1 = su.getRequest();
        String name = new String(req1.getParameter("name").getBytes(),"utf-8");
        String categoryId1 = req1.getParameter("categoryId");
        int categoryId=Integer.parseInt(categoryId1);
        int price=Integer.parseInt(req1.getParameter("price"));
        String itemdesc = new String(req1.getParameter("itemdesc").getBytes(),"utf-8");
        int inventory=Integer.parseInt(req1.getParameter("inventory"));
        DAOJUCHENG_ITEM i=new DAOJUCHENG_ITEM(0,categoryId,name,price,image,itemdesc,inventory);
        int count = DaoJuChengDao.itemadd(i);
        if(count>0) {
            response.sendRedirect("/manage/admin_doitemselect");
        }else {
            PrintWriter out=response.getWriter();
            out.write("<script>");
            out.write("alert('商品添加失败')");
            out.write("location.href='/manage/admin_itemadd.jsp'");
            out.write("</script>");
        }
    }

}
