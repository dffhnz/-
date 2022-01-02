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

@WebServlet("/manage/admin_doorderupdate")
public class admin_DoOrderUpdate extends HttpServlet {
    public admin_DoOrderUpdate() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String id = request.getParameter("id");
        DAOJUCHENG_SHOPPING s=DaoJuChengDao.orderselect1(Integer.parseInt(id));
        DaoJuChengDao.orderupdate(s);
        DAOJUCHENG_ITEM i=DaoJuChengDao.selectByitemID(String.valueOf(s.getIid()));
        DAOJUCHENG_ITEM ni=new DAOJUCHENG_ITEM(i.getId(),i.getCategoryId(),i.getName(),i.getPrice(),i.getImage(),i.getItemdesc(),i.getInventory()-s.getNum());
        DaoJuChengDao.itemupdate(ni);
        response.sendRedirect("admin_doorderselect");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String ids[] =request.getParameterValues("id[]");
        for (int j = 0; j < ids.length; j++) {
            DAOJUCHENG_SHOPPING s = DaoJuChengDao.orderselect1(Integer.parseInt(ids[j]));
            DAOJUCHENG_ITEM i = DaoJuChengDao.selectByitemID(String.valueOf(s.getIid()));
            DaoJuChengDao.orderupdate(s);
            DAOJUCHENG_ITEM ni = new DAOJUCHENG_ITEM(i.getId(), i.getCategoryId(), i.getName(), i.getPrice(), i.getImage(), i.getItemdesc(), i.getInventory() - s.getNum());
            DaoJuChengDao.itemupdate(ni);
        }
        response.sendRedirect("admin_doorderupdate");
    }
}
