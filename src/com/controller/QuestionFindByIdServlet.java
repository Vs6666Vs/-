package com.controller;

import com.Dao.QuestionDao;
import com.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionFindByIdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 通过请求对象获取请求参数
        String questionId = request.getParameter("questionId");
        // 调用DAO类获取查询结果
        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.findById(request,questionId);

        // 调用请求对象将查询结果放入共享数据
        request.setAttribute("key",question);

        request.getRequestDispatcher("/question_update.jsp").forward(request,response);
    }
}
