package com.controller;

import com.Dao.QuestionDao;
import com.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class QuestionFindServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用DAO类发送查询语句
        QuestionDao questionDao = new QuestionDao();
        List<Question> list = questionDao.findAll(request);

        request.setAttribute("key",list);
        request.getRequestDispatcher("/question.jsp").forward(request,response);
    }
}
