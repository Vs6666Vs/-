package com.controller;

import com.Dao.QuestionDao;
import com.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class QuestionRandServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用DAO得到出的题目
        QuestionDao questionDao = new QuestionDao();
        List<Question> list = questionDao.findRand(request);
        // 将list集合写到session中
        HttpSession session = request.getSession(false);
        session.setAttribute("key",list);

        request.getRequestDispatcher("/exam.jsp").forward(request,response);
    }
}
