package com.my.lab.application;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SwaggerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse responce = (HttpServletResponse) servletResponse;
        responce.addHeader("Access-Control-Allow-Origin", "*");
        responce.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        responce.addHeader("Access-Control-Allow-Headers", "Content-Type");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
