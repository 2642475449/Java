package com.imooc.jdbc.hrapp.command;

import java.sql.*;
import java.util.Scanner;

public class QueryCommand implements Command {


    @Override
    public void execute() {
        System.out.println("请输入部门");
        //键盘输入
        Scanner scannerIn = new Scanner(System.in);
        //键盘输入后得到的字符串
        String pdname = scannerIn.nextLine();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try {//第一步：把想要连接的数据库驱动加载入JVM，如加载mysql数据库驱动类可以通过`Class.forName("com.mysql.cj.jdbc.Driver");`加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //第二步：使用`DriverManager.getConnection(String url , String username , String password)`创建数据库连接
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/imooc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                    "root",
                    "448866293"
            );
            //第三步：利用**上一步的数据库连接**创建Statement
            statement = connection.createStatement();
            //结果集
            System.out.println("SELECT * FROM employee WHERE dname = '" + pdname + "'");
            rs = statement.executeQuery("SELECT * FROM employee WHERE dname = '" + pdname + "'");
            //第四步：遍历查询结果
            while (rs.next()) {
                Integer eno = rs.getInt(1);//表示把数据表的第一行提取出来
                String ename = rs.getString("ename");
                float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            //第五步：关闭连接，释放资源
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
