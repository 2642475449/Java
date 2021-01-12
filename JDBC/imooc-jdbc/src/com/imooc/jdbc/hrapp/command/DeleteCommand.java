package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.Common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 删除员工数据
 */
public class DeleteCommand implements Command {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入员工编号");
        Integer eno = scanner.nextInt();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DbUtils.getConnection();
            String sql = "DELETE FROM employee WHERE eno = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,eno);
            int cnt = statement.executeUpdate();
            if (cnt == 1){
                System.out.println("员工离职完毕");
            } else {
                System.out.println("未找到" + eno + "编号员工数据");
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
