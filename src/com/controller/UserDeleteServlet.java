package com.controller;

import com.Dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId;
        int result;
        PrintWriter out = null;
        UserDao userDao = new UserDao();
        // 通过请求对象 获取请求参数
        userId = request.getParameter("userId");
        System.out.println(userId);
        // 调用DAO类将sql命令发送到数据库，并返回结果
        result = userDao.delete(userId);
        // 调用响应对象 将返回结果以二进制形式写入到响应体中
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
        if (result == 1){
            out.print("<font style='color:red;font-size:40'>用户信息删除成功</font>");
        }else {
            out.print("<font style='color:red;font-size:40'>用户信息删除失败</font>");
        }
    }
}
