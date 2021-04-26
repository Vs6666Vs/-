package com.controller;

import com.Dao.QuestionDao;
import com.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionAddServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title,optionA,optionB,optionC,optionD,answer;
        int result;
        QuestionDao questionDao = new QuestionDao();
        //1、调用请求对象获取请求包中的请求参数
        title = request.getParameter("title");
        optionA = request.getParameter("optionA");
        optionB = request.getParameter("optionB");
        optionC = request.getParameter("optionC");
        optionD = request.getParameter("optionD");
        answer = request.getParameter("answer");
        //2、调用Dao类将Insert命令推送到数据库，并得到处理结果
        Question question = new Question(null,title,optionA,optionB,optionC,optionD,answer);
        result = questionDao.add(request,question);
        //3、通过请求转发，向Tomcat索要info.jsp将处理结果写入到响应体
        if (result == 1){
            request.setAttribute("info","试题添加成功");
        }else {
            request.setAttribute("info","试题添加失败");
        }
        request.getRequestDispatcher("/info.jsp").forward(request,response);
    }
}
