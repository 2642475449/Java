package com.imooc.jdbc.shop.command;

import com.imooc.jdbc.Common.DbUtils;

import java.sql.*;
import java.util.Scanner;

public class PstmtQueryCommand implements Command {

    @Override
    public void execute() {
        System.out.println("请输入预算金额");
        //键盘输入
        Scanner moneyNumber = new Scanner(System.in);
        //键盘输入后得到的字符串
        Integer price = moneyNumber.nextInt();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        try {
            connection = DbUtils.getConnection();
            //第三步：利用上一步的数据库连接创建Statement
            String sql = "SELECT * FROM goods WHERE price <= ? and price >= ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,price);
            preparedStatement.setInt(2,1500);

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
            DbUtils.closeConnection(connection);
        }


    }
}
