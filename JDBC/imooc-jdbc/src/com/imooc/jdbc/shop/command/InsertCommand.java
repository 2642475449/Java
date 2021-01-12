package com.imooc.jdbc.shop.command;

import com.imooc.jdbc.Common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 添加商品
 */
public class InsertCommand implements Command {
    @Override
    public void execute() {
        System.out.println("请输入商品名称");

       Scanner scanner = new Scanner(System.in);
        String name = scanner.next();

        System.out.println("请输入商品价格");
        float price = scanner.nextFloat();

        System.out.println("请输入商品描述");
        String desp = scanner.next();

        System.out.println("请输入创建时间");
        String createTime = scanner.next();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Date createDate = null;

        //日期转化
        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
        try {
             createDate = sdt.parse(createTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long createDateTime = createDate.getTime();
        java.sql.Date date = new java.sql.Date(createDateTime);

        try {
            //创建新的数据库连接
            connection = DbUtils.getConnection();
            String sql = "INSERT INTO goods(name,price,desp,create_time) VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setFloat(2,price);
            preparedStatement.setString(3,desp);
            preparedStatement.setDate(4,date);
            int i = preparedStatement.executeUpdate();
            if (i > 0) {
                System.out.println(name + "已经添加成功");
            } else {
                System.out.println("添加失败");
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
