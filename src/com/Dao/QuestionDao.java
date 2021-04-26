package com.Dao;

import com.entity.Question;
import com.util.JdbcUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
    private JdbcUtil jdbcUtil = new JdbcUtil();

    public int add(HttpServletRequest request, Question question){
        PreparedStatement ps;
        int result = 0;
        String sql = "insert into question(title,optionA,optionB,optionC,optionD,answer) values(?,?,?,?,?,?)";
        ps = jdbcUtil.createStatement(request,sql);
        try {
            ps.setString(1,question.getTitle());
            ps.setString(2,question.getOptionA());
            ps.setString(3,question.getOptionB());
            ps.setString(4,question.getOptionC());
            ps.setString(5,question.getOptionD());
            ps.setString(6,question.getAnswer());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return result;
    }

    public List<Question> findAll(HttpServletRequest request){
        Integer questionId;
        String title,optionA,optionB,optionC,optionD,answer;
        String sql = "select * from question";
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Question> list = new ArrayList<>();
        ps = jdbcUtil.createStatement(request,sql);
        try {
            rs = ps.executeQuery();

            while (rs.next()){
                questionId = rs.getInt("questionId");
                title = rs.getString("title");
                optionA = rs.getString("optionA");
                optionB = rs.getString("optionB");
                optionC = rs.getString("optionC");
                optionD = rs.getString("optionD");
                answer = rs.getString("answer");
                Question question = new Question(questionId,title,optionA,optionB,optionC,optionD,answer);
                list.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(rs);
        }
        return list;
    }

    public int delete(HttpServletRequest request, String questionId) {
        PreparedStatement ps = null;
        int result = 0;
        String sql = "delete from question where questionId =?";
        ps = jdbcUtil.createStatement(request,sql);

        try {
            ps.setString(1,questionId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return result;
    }

    public Question findById(HttpServletRequest request, String questionId) {
        String title,optionA,optionB,optionC,optionD,answer;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Question question = null;
        String sql = "select * from question where questionId=?";
        ps = jdbcUtil.createStatement(request,sql);
        try {
            ps.setString(1,questionId);

            rs = ps.executeQuery();
            while (rs.next()){

                title = rs.getString("title");
                optionA = rs.getString("optionA");
                optionB = rs.getString("optionB");
                optionC = rs.getString("optionC");
                optionD = rs.getString("optionD");
                answer = rs.getString("answer");
                question = new Question(Integer.valueOf(questionId),title,optionA,optionB,optionC,optionD,answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(rs);
        }
        return question;
    }

    public int update(HttpServletRequest request, Question question) {
        PreparedStatement ps = null;
        int result = 0;
        String sql = "update question set title=?,optionA=?,optionB=?,optionC=?,optionD=?,answer=? where questionId =?";
        ps = jdbcUtil.createStatement(request,sql);

        try {
            ps.setString(1,question.getTitle());
            ps.setString(2,question.getOptionA());
            ps.setString(3,question.getOptionB());
            ps.setString(4,question.getOptionC());
            ps.setString(5,question.getOptionD());
            ps.setString(6,question.getAnswer());
            ps.setInt(7,question.getQuestionId());

            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return result;
    }

    public List<Question> findRand(HttpServletRequest request) {
        Integer questionId;
        String title,optionA,optionB,optionC,optionD,answer;
        List<Question> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from question order by rand() limit 0,5";
        ps = jdbcUtil.createStatement(request,sql);
        try {
            rs = ps.executeQuery();

            while (rs.next()){
                questionId = rs.getInt("questionId");
                title = rs.getString("title");
                optionA = rs.getString("optionA");
                optionB = rs.getString("optionB");
                optionC = rs.getString("optionC");
                optionD = rs.getString("optionD");
                answer = rs.getString("answer");
                Question question = new Question(questionId,title,optionA,optionB,optionC,optionD,answer);
                list.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(rs);
        }
        return list;
    }
}
