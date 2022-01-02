package com.daojucheng.servlet;
import com.daojucheng.entity.DAOJUCHENG_ITEM;
import com.daojucheng.entity.DAOJUCHENG_SHOPPING;
import com.daojucheng.entity.DAOJUCHENG_USER;
import com.daojucheng.service.DaoJuChengDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/user_doshoppingadd")
public class user_DoShoppingAdd extends HttpServlet {
    public user_DoShoppingAdd() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session=request.getSession();
        DAOJUCHENG_USER u = (DAOJUCHENG_USER) session.getAttribute("user_list");
        String id = request.getParameter("id");
        String num1 = request.getParameter("num");
        int num2=1;
        if (num1!=null){
            num2=Integer.parseInt(num1);
        }
        DAOJUCHENG_ITEM i= DaoJuChengDao.selectByitemID(id);
        int count=DaoJuChengDao.selectByitemIDandByuserID(i.getId(),u.getUser_id());
        if(count>0){
            DAOJUCHENG_SHOPPING s = DaoJuChengDao.shoppingByitemIDandByuserID(i.getId(), u.getUser_id());
            DAOJUCHENG_SHOPPING s1=new DAOJUCHENG_SHOPPING(s.getId(),s.getCategory(),s.getIid(),s.getName(),s.getImage(),s.getPrice(),s.getUid(),s.getNum()+num2,1);
            DaoJuChengDao.shoppingupdate(s1);
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('加入购物车成功!...');");
            out.write("location.href='item_doitemdetails?id="+id+"'");
            out.write("</script>");
        }else{
            DAOJUCHENG_SHOPPING s=new DAOJUCHENG_SHOPPING(0,String.valueOf(i.getCategoryId()),i.getId(),i.getName(),i.getImage(),i.getPrice(),u.getUser_id(),1,1);
            DaoJuChengDao.shoppingadd(s);
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('加入购物车成功!');");
            out.write("location.href='item_doitemdetails?id="+id+"'");
            out.write("</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
