package com.imooc.jdbc.shop.command;

import java.sql.*;
import java.util.Scanner;

public class QueryCommand implements Command {

    @Override
    public void execute() {
        System.out.println("请输入预算金额");
        //键盘输入
        Scanner moneyNumber = new Scanner(System.in);
        //键盘输入后得到的字符串
        Float price = moneyNumber.nextFloat();
        Connection connection = null;
        Statement statement = null;
        ResultSet set = null;
        try {
            //第一步：把想要连接的数据库驱动加载入JVM，如加载mysql数据库驱动类可以通过Class.forName("com.mysql.cj.jdbc.Driver");加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //第二步：使用DriverManager.getConnection(String url , String username , String password)创建数据库连接
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/imooc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                    "root",
                    "448866293"
            );
            //第三步：利用上一步的数据库连接创建Statement
            statement = connection.createStatement();
            set = statement.executeQuery("SELECT * FROM goods WHERE price <= " + price + ";");
            //第四步：遍历查询结果
            while (set.next()) {
                Integer id = set.getInt(1);//表示把数据表的第一行提取出来
                String name = set.getString("name");
                float price1 = set.getFloat("price");
                String desp = set.getString("desp");
                System.out.println(id + "-" + name + "-" + price1 + "-" + desp);
            }

        } catch (ClassNotFoundException | SQLException e) {
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
