package com.edu.nefu.servlet;

import com.edu.nefu.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        User user = new User(request.getParameter("un"), request.getParameter("pw"));
        boolean login;
        try {
            login = user.login();
        } catch (Exception e) {
            login = false;
        }
        if (login) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/javaweb_war_exploded/ex7/login_servlet/login_s.html");
        } else {
            out.print("用户名密码不正确，3秒钟后重新登录");
            response.setHeader("refresh", "3;URL=ex7/login_servlet/login.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
