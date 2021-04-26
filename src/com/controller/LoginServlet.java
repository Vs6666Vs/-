package com.controller;

import com.Dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName,password;
        UserDao userDao = new UserDao();
        int result = 0;
        // 调用请求对象设置编码
        request.setCharacterEncoding("utf-8");
        // 调用请求对象获取请求参数
        userName = request.getParameter("userName");
        password = request.getParameter("password");
        // 调用DAO将sql发送到数据库
        result = userDao.login(userName,password);
        //调用响应对象，根据验证结果将不同【资源文件地址】写入到响应头，交给浏览器
        if (result==1){
            // 给登录成功的用户发放令牌
            request.getSession();
            response.sendRedirect("/myWeb/index.html");
        }else {
            response.sendRedirect("/myWeb/login_error.html");
        }
    }
}
