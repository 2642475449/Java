package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.Common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 修改员工数据
 */
public class UpdateCommand implements Command {

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入员工编号");
        int eno = scanner.nextInt();

        System.out.print("请输入员工新的薪资:");
        float salary = scanner.nextFloat();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //声明连接
            connection = DbUtils.getConnection();
            String sql = "UPDATE employee SET salary=? WHERE eno=?";
            statement = connection.prepareStatement(sql);
            statement.setFloat(1,salary);
            statement.setInt(2,eno);
            int cnt = statement.executeUpdate();
            if (cnt == 1){
                System.out.println("员工薪调整完毕");
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
