package com.imooc.jdbc.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StandardJDBC {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            //1.加载并注册JDBC驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.创建数据库连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/imooc?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai",
                    "root",
                    "448866293");
            //3.创建statement对象
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select  * from employee");
            //4.遍历查询结果
            while (resultSet.next()) {
                int eno = resultSet.getInt(1);
                String ename = resultSet.getString("ename");
                float salary = resultSet.getFloat("salary");
                String dname = resultSet.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && conn.isClosed() == false) {
                    //5.关闭连接，释放资源
                    conn.close();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }


    }
}
