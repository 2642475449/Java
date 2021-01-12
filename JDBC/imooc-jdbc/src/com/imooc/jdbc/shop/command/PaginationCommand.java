package com.imooc.jdbc.shop.command;

import com.imooc.jdbc.Common.DbUtils;
import com.imooc.jdbc.shop.entity.Goods;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 分页显示
 * @author xiaohang
 * @version 0.1
 */
public class PaginationCommand implements Command {

    @Override
    public void execute() {
        System.out.println("请输入页码");
        Scanner scanner = new Scanner(System.in);
        int page = scanner.nextInt();

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Goods> list = new ArrayList<>();
        try {
            conn = DbUtils.getConnection();
            String sql = "SELECT * FROM goods LIMIT ?,10;";
            statement = conn.prepareStatement(sql);
            statement.setInt(1,(page - 1) * 10);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                float price = resultSet.getFloat("price");
                String desp = resultSet.getString("desp");
                Date createTime = resultSet.getDate("create_time");
                Goods goods = new Goods();
                goods.setId(id);
                goods.setName(name);
                goods.setPrice(price);
                goods.setDesp(desp);
                goods.setCreate_time(createTime);
                list.add(goods);
            }
            System.out.println(list.size());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(conn);
        }
    }
}
