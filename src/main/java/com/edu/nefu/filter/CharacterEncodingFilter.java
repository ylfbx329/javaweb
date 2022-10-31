package com.edu.nefu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CharacterEncodingFilter")
public class CharacterEncodingFilter implements Filter {

    FilterConfig cofigF = null;
    private String encoding;
    private boolean forceEncoding;

    public void init(FilterConfig config) throws ServletException {
        this.cofigF = config;
        encoding = config.getInitParameter("encoding");
        forceEncoding = Boolean.parseBoolean(config.getInitParameter("forceEncoding"));
    }

    public void destroy() {
        cofigF = null;
        encoding = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // 清除缓存
        httpServletResponse.setDateHeader("Expires", 0);
        // 注意这里是两个，很多博客少了no-store是不成功的
        httpServletResponse.setHeader("Cache-Control", "no-cache, no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");

        if (this.encoding != null && (this.forceEncoding || request.getCharacterEncoding() == null)) {
            request.setCharacterEncoding(this.encoding);
            if (this.forceEncoding) {
                response.setCharacterEncoding(this.encoding);
            }
        }
        String string = String.valueOf(httpServletRequest.getRequestURL());
        if (!string.equals("http://localhost:8080/javaweb_war_exploded/"))
            System.out.println("过滤到" + string);
        chain.doFilter(request, response);
    }
}
