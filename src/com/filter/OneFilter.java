package com.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
// 过滤器：防止恶意登录
public class OneFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 对所有访问的请求进行令牌判断
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        // 调用请求对象获取URI，了解用户调用的资源文件与什么有关
        String URI = request.getRequestURI();
        // 如果访问的是登录有关的资源文件，则直接放行
        if (URI.indexOf("login") != -1 || "/myWeb/".equals(URI)){
            servletRequest.setCharacterEncoding("utf-8");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        // 如果访问的是其他的资源文件，则判断是否为合法访问
        HttpSession session = request.getSession(false);
        if (session == null){
            request.getRequestDispatcher("/login_error.html").forward(servletRequest,servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
