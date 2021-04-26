package com.controller;

import com.Dao.UserDao;
import com.entity.Users;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserFindServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Users> userList;
        PrintWriter out;
        // 调用【Dao类】将sql命令发送到数据库，并返回结果集
        UserDao userDao = new UserDao();
        userList = userDao.findAll();
        // 通过响应对象将结果集以二进制的形式写入响应体
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
        out.print("<table border='2' align='center'>");
        out.print("<tr>");
        out.print("<td>用户编号</td>");
        out.print("<td>用户姓名</td>");
        out.print("<td>用户密码</td>");
        out.print("<td>用户性别</td>");
        out.print("<td>用户邮箱</td>");
        out.print("<td colspan=2 align=center>操作</td>");
        out.print("</tr>");
        for (Users users : userList) {
            out.print("<tr>");
            out.print("<td>" + users.getUserId() + "</td>");
            out.print("<td>" + users.getUserName() + "</td>");
            out.print("<td>******</td>");
            out.print("<td>" + users.getSex() + "</td>");
            out.print("<td>" + users.getEmail() + "</td>");
            out.print("<td><a href='/myWeb/user/delete?userId="+users.getUserId()+"'>删除用户</a></td>");
            out.print("<td><a href='/myWeb/user/update?userId="+users.getUserId()+"'>更新用户</a></td>");
            out.print("</tr>");
        }
        out.print("</table>");
    }
}
