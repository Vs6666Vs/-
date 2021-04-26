package com.listener;

import com.util.JdbcUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 全局作用域监听器
 * 对于全局作用域对象的初始化和销毁作监听
 * 模拟数据库连接池
 */
public class OneListener implements ServletContextListener {
    // 对初始化监听
    //在Tomcat启动时，预先创建20个Connection,在userDao.add方法执行时
    //将实现创建好connection交给add方法
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        JdbcUtil jdbcUtil = new JdbcUtil();
        Map map = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            Connection conn =  jdbcUtil.getCon();
            System.out.println("服务器启动，创建Connection" + conn);
            map.put(conn,true);// 将Connection对象存放到map集合中
                               // true表示该连接对象处于空闲
        }
        // 为了Connection能在服务器运行阶段一直使用，将其添加到共享数据中
        ServletContext application = sce.getServletContext();
        application.setAttribute("key1",map);
    }
    // 对销毁监听
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext application = sce.getServletContext();
        Map map = (Map)application.getAttribute("key1");
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Connection conn = (Connection)iterator.next();
            if (conn != null) {
                System.out.println(conn + "被销毁");
            }
        }
    }
}
