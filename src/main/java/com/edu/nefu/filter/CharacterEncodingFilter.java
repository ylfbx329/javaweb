package com.edu.nefu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
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
//        response.setCharacterEncoding("utf-8");
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
        if (this.encoding != null && (this.forceEncoding || request.getCharacterEncoding() == null)) {
            request.setCharacterEncoding(this.encoding);
            if (this.forceEncoding) {
                response.setCharacterEncoding(this.encoding);
            }
        }
        chain.doFilter(request, response);
    }
}
