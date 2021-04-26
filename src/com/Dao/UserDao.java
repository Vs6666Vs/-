package com.Dao;

import com.entity.Users;
import com.util.JdbcUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private JdbcUtil jdbcUtil = new JdbcUtil();
    // 用户添加insert
    public int add(Users users){
        String sql = "insert into users(userName,password,sex,email) values(?,?,?,?)";
        int result = 0;
        // 获取PreparedStatement数据库操作对象
        try {
            PreparedStatement ps =  jdbcUtil.createStatement(sql);
            // 通过实体类获取信息
            ps.setString(1,users.getUserName());
            ps.setString(2,users.getPassword());
            ps.setString(3,users.getSex());
            ps.setString(4,users.getEmail());
            // 执行sql语句
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return result;
    }

    public int add(Users user, HttpServletRequest request){
        String sql ="insert into users(userName,password,sex,email)" +
                " values(?,?,?,?)";
        PreparedStatement ps = jdbcUtil.createStatement(request,sql);
        int result = 0;
        try {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getSex());
            ps.setString(4, user.getEmail());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(request);
        }
        return result;
    }

    // 查看用户select
    public List<Users> findAll(){
        String sql = "select * from users";
        List<Users> list = new ArrayList<>();
        // 获取数据库操作对象
        PreparedStatement ps = jdbcUtil.createStatement(sql);
        ResultSet rs = null;
        try {
            // 执行sql语句
            rs = ps.executeQuery();
            // 遍历结果集，将结果集中的元素存放到List集合中
            // 因为我们需要释放资源同时又需要执行结果返回，所以需要把结果集中的内容取出
            while (rs.next()){
                Integer integer = rs.getInt("userId");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                String sex = rs.getString("sex");
                String email = rs.getString("email");
                Users users = new Users(integer,userName,password,sex,email);
                list.add(users);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(rs);
        }
        return list;
    }

    // 删除用户delete
    public int delete(String userId){
        int result = 0;
        String sql = "delete from users where userId=?";
        try {
            // 获取数据库操作对象
            PreparedStatement ps = jdbcUtil.createStatement(sql);
            ps.setString(1,userId);
            // 执行sql
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return result;
    }

    // 用户登录
    public int login(String userName,String password){
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        String sql = "select count(*) from users where userName=? and password=?";
        try {
            // 获取数据库操作对象
            ps = jdbcUtil.createStatement(sql);
            ps.setString(1,userName);
            ps.setString(2,password);
            // 执行SQL
            rs = ps.executeQuery();
            // 判断
            while (rs.next()){
                result = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close(rs);
        }
        return result;
    }
}
