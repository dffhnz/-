package com.daojucheng.Filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class Register
 */
@WebFilter("/manage/register")
public class Register implements Filter {

    /**
     * Default constructor. 
     */
    public Register() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		String userName=req.getParameter("userName");
		PrintWriter out= resp.getWriter();
		
		if(userName.equals("")) {
			out.write("<script>");
			out.write("alert('用户名不能为空');");
			out.write("location.href='/DaoJuCheng/enroll.jsp';");
			out.write("</script>");
			out.close();
			return;
		}
		//不通过则直接return;
		chain.doFilter(req, resp);//通过则使用这条语句
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
