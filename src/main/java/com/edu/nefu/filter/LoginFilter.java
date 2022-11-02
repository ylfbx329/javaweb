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
        String passURL = rq.getServletPath();
        PrintWriter out = rp.getWriter();
        out.print(passURL + "<BR>");
        if (!(passURL.equals("/ex7/login_servlet/login.html") || passURL.equals("/LoginServlet"))) {
            if (session.getAttribute("user") == null) {
                out.print("先登录");
                rp.setHeader("refresh", "5;url=ex7/login_servlet/login.html");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
