package com.imooc.jdbc.shop.command;

import com.imooc.jdbc.Common.DbUtils;
import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 删除商品
 */
public class DeleteCommand implements Command{
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要删除的商品名称");
        String name = scanner.next();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //创建数据库链接
             connection = DbUtils.getConnection();
            String sql = "DELETE FROM goods WHERE `name`=?";
             statement = connection.prepareStatement(sql);
            statement.setString(1,name);
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
