package com.command;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;

/**
 * 删除新闻
 * 先查询所有的新闻列表，根据新闻列表中的id删除新闻。
 */

public class DeleteNews implements Command {

    Connection connection = null;


    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要删除的新闻id");
        String id = scanner.next();

        try {
            //读取配置文件
            Properties properties = new Properties();
            //得到配置文件路径
            String path = InsertNews.class.getResource("/druid-config.properties").getPath();
            //防止乱码
            String decode = new URLDecoder().decode(path, "UTF-8");
            //载入
            properties.load(new FileInputStream(decode));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            // 建立连接
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            QueryRunner queryRunner = new QueryRunner(dataSource);
            String delete = "delete from news where id=?";

            int update = queryRunner.update(connection, delete, new Object[]{id});

            if (update == 1) {
                System.out.println("删除成功");
                connection.commit();
            } else {
                System.out.println("删除失败");
            }


        } catch (Exception e) {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.rollback();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throwables.printStackTrace();
            }
        }
    }
}
