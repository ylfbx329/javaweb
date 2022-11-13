package com.edu.nefu.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest rq = (HttpServletRequest) request;
        HttpServletResponse rp = (HttpServletResponse) response;
        HttpSession session = rq.getSession();
        String passURL = rq.getRequestURI().replace(rq.getContextPath(), "");
        PrintWriter out = rp.getWriter();
        if (session.getAttribute("user") != null || passURL.equals("/ex7/login_servlet/login.html") || passURL.equals("/ex7/login_servlet/register.html") || passURL.equals("/ex7/login_servlet/register_s.html") || passURL.equals("/ex7/login_servlet/form.css") || passURL.equals("/LoginServlet") || passURL.equals("/RegisterServlet")) {
            chain.doFilter(request, response);
        } else {
            response.setContentType("text/plain;charset=UTF-8");
            out.print("先登录");
            out.flush();
            out.close();
            rp.setHeader("refresh", "5;url=ex7/login_servlet/login.html");
        }
    }
}
