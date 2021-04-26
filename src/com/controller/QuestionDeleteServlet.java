package com.controller;

import com.Dao.QuestionDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QuestionDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int result = 0;
        // 从请求包中获取请求参数
        String questionId = request.getParameter("questionId");
        // 调用DAO类发送sql命令
        QuestionDao questionDao = new QuestionDao();
        result = questionDao.delete(request, questionId);
        if (result == 1) {
            request.setAttribute("info", "试题删除成功");
        } else {
            request.setAttribute("info", "试题删除失败");
        }
        request.getRequestDispatcher("/info.jsp").forward(request, response);
    }
}
