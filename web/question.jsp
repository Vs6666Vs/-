<%@ page import="com.entity.Question" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: liuhan
  Date: 2021/4/18
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List<Question> questionList =(List)request.getAttribute("key");
%>

<table border="2" align="center">
    <tr align="center">
        <td>试题编号</td>
        <td>题目信息</td>
        <td>A选项</td>
        <td>B选项</td>
        <td>C选项</td>
        <td>D选项</td>
        <td>正确答案</td>
        <td colspan="2">操作</td>
    </tr>
    <%
        for(Question q:questionList){
    %>
    <tr>
        <td><%=q.getQuestionId()%></td>
        <td><%=q.getTitle()%></td>
        <td><%=q.getOptionA()%></td>
        <td><%=q.getOptionB()%></td>
        <td><%=q.getOptionC()%></td>
        <td><%=q.getOptionD()%></td>
        <td><%=q.getAnswer()%></td>
        <td>
            <a href="/myWeb/question/delete?questionId=<%=q.getQuestionId()%>">删除试题</a>
        </td>
        <td>
            <a href="/myWeb/question/findById?questionId=<%=q.getQuestionId()%>">详细信息</a>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
