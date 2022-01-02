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
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/manage/admin_DoUserUpdate")
public class admin_DoItemUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin_DoItemUpdate() {
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
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
            f.saveAs("C:/Users/杨某人/Desktop/img/"+image,File.SAVEAS_PHYSICAL);
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }
        Request req1 = su.getRequest();
        String Id1=request.getParameter("id");
        int id= Integer.parseInt(Id1);
        String name = new String(req1.getParameter("name").getBytes(),"utf-8");
        String categoryId1 = req1.getParameter("categoryId");
        int categoryId=Integer.parseInt(categoryId1);
        int price=Integer.parseInt(req1.getParameter("price"));
        String itemdesc = new String(req1.getParameter("itemdesc").getBytes(),"utf-8");
        int inventory=Integer.parseInt(req1.getParameter("inventory"));
        DAOJUCHENG_ITEM i=new DAOJUCHENG_ITEM(id,categoryId,name,price,image,itemdesc,inventory);
        int count = DaoJuChengDao.itemupdate(i);
        if(count>0) {
            response.sendRedirect("admin_doitemselect?cp="+request.getParameter("cpage"));
        }else {
            PrintWriter out=response.getWriter();
            out.write("<script>");
            out.write("alert('用户修改失败!');");
            out.write("location.href='admin_doitemselect?id="+id+"'");
            out.write("</script>");
        }
    }
}
