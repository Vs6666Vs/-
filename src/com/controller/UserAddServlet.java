package com.controller;

import com.Dao.UserDao;
import com.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class UserAddServlet extends HttpServlet {
    //
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName,password,sex,email;
        int result = 0;
        PrintWriter out = null;
        // 1、调用【请求对象】获取【请求头】中的参数信息
        userName = request.getParameter("userName");
        password = request.getParameter("password");
        sex = request.getParameter("sex");
        email = request.getParameter("email");
        // 创建实体类
        Users users = new Users(null,userName,password,sex,email);
        // 2、调用【Dao类】将用户信息以sql命令发送到数据库，并【返回结果】
        UserDao userDao = new UserDao();
        //Date startDate = new Date();
        result = userDao.add(users,request);
        //Date endDate = new Date();
        //System.out.println("添加消耗时间 = "+(endDate.getTime() - startDate.getTime())+"毫秒");
        // 3、调用【相应对象】将返回结果以二进制的形式写入响应体中
        // 通过【响应对象】设置编译器和编码方式
        response.setContentType("text/html;charset=utf-8");
        // 通过【响应对象】获取流
        out = response.getWriter();
        if (result == 1){
            // 通过流写入
            out.print("<font style='color:red;font-size:40'>用户信息注册成功</font>");
        }else {
            out.print("<font style='color:red;font-size:40'>用户信息注册失败</font>");
        }
    }
}
