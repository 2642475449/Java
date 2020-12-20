package com.imooc.jdbc.hrapp.command;

import java.sql.*;
import java.util.Scanner;

public class QueryCommend implements Command {
    @Override
    public void execute() {
        System.out.println("请输入部门");
        Scanner in = new Scanner(System.in);
        String pdname = in.next();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //第一步：把想要连接的数据库驱动加载入JVM，如加载mysql数据库驱动类可以通过`Class.forName("com.mysql.cj.jdbc.Driver");`加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //第二步：使用`DriverManager.getConnection(String url , String username , String password)`创建数据库连接
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/imooc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                    "root",
                    "448866293"
            );

            //第三步：利用**上一步的数据库连接**创建Statement
             stmt = conn.createStatement();
            //结果集
             rs = stmt.executeQuery("SELECT * from employee WHERE dname='" + pdname + "'");
            //第四步：遍历查询结果
            while (rs.next()) {
                Integer eno = rs.getInt(1);
                String ename = rs.getString("ename");
                float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {

            //第五步：关闭连接，释放资源
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
