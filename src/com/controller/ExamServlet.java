package com.controller;

import com.entity.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ExamServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    //http://localhost:8080/myWeb/exam?answer_9=A&answer_10=A&answer_7=A&answer_11=A&answer_1=A
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        List<Question> questionList = (List)session.getAttribute("key");
        int score = 0;
        for (Question question : questionList){
            String answer = question.getAnswer();
            String userAnswer = request.getParameter("answer_" + question.getQuestionId());
            if (answer.equals(userAnswer)){
                score+=20;
            }
        }
        //4.将分数写入到request中，作为共享数据
        request.setAttribute("info", "本次考试成绩: "+score);
        //5.请求转发调用jsp将用户本次考试分数写入到响应体
        request.getRequestDispatcher("info.jsp").forward(request, response);
    }
}
