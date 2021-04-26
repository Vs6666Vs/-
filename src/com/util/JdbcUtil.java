package com.util;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;

public class JdbcUtil {

     final String URL="jdbc:mysql://localhost:3306/bjpowernode";
     final String USERNAME="root";
     final String PASSWORD="liuhan";
     PreparedStatement ps= null;
     Connection con = null;

    //将jar包中driver实现类加载到JVM中
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //-------------通过全局作用域对象得到Connection-----------start
    public Connection getCon(HttpServletRequest request){
        // 通过全局作用域对象获取Connection
        ServletContext application = request.getServletContext();
        Map map= (Map)application.getAttribute("key1");
        // 遍历map集合
        Iterator iterator = map.keySet().iterator();
        while(iterator.hasNext()){
            con = (Connection)iterator.next();
            boolean flag = (boolean)map.get(con);
            if(flag == true){
                map.put(con, false);
                break;
            }
        }
        return con;
    }

    public PreparedStatement createStatement(HttpServletRequest request,String sql){
        try {
            ps = getCon(request).prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
    public void close(HttpServletRequest request){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ServletContext application = request.getServletContext();
        Map map =(Map)application.getAttribute("key1");
        map.put(con, true);
    }
    //-------------通过全局作用域对象得到Connection-----------end

    //封装连接通道创建细节
    public Connection getCon(){

        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    //封装交通工具创建细节
    public PreparedStatement createStatement(String sql){

        try {
            ps =  getCon().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
    // ps与con销毁细节 insert,update,delete
    public void close(){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

     //select ps,con,rs
    public void close(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close();
    }

}
