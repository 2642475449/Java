package com.imooc.jdbc.Common;

import java.sql.*;

public class DbUtils {
    /**
     * 创建新的数据库连接
     * @return 新的Connection对象
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        //第一步：把想要连接的数据库驱动加载入JVM，如加载mysql数据库驱动类可以通过Class.forName("com.mysql.cj.jdbc.Driver");加载并注册JDBC驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //第二步：使用DriverManager.getConnection(String url , String username , String password)创建数据库连接
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/imooc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                "root",
                "448866293"
        );
        return connection;
    }

    /**
     * 关闭连接，释放资源
     * @param connection
     */
    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
