package com.imooc.jdbc.shop.command;

import com.imooc.jdbc.Common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 修改商品
 */
public class UpdateCommand implements Command {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要修改的商品名称");
        String name = scanner.next();
        System.out.println("请输入新的金额");
        float price = scanner.nextFloat();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //创建新的数据库连接
             connection = DbUtils.getConnection();
            String sql = "UPDATE goods SET `price`=? WHERE `name`=?";
             statement = connection.prepareStatement(sql);
            statement.setFloat(1,price);
            statement.setString(2,name);
            int i = statement.executeUpdate();
            if (i > 0){
                System.out.println("调整完毕");
            } else {
                System.out.println("未找到" + name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(connection);
        }
    }
}
