package com.imooc.jdbc.shop.sample;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.imooc.jdbc.shop.entity.Goods;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 *
 */
public class DbutilsSample {
    private static void insert() {
        Connection connection = null;

        //创建读取配置文件
        Properties properties = new Properties();
        String path = DbutilsSample.class.getResource("/druid-config.properties").getPath();
        try {

            String decode = new URLDecoder().decode(path, "UTF-8");
            properties.load(new FileInputStream(decode));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
             connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            String sql1="INSERT INTO goods(name,price,desp,create_time) VALUES(?,?,?,?)";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql1,new Object[]{"照相机",5000,"防水","1999/1/30"});
            connection.commit();
            query();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (connection !=null && !connection.isClosed()) {
                    connection.rollback();
                    System.out.println("添加失败");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
    private static void query(){
        //创建读取配置文件
        Properties properties = new Properties();
        String path = DbutilsSample.class.getResource("/druid-config.properties").getPath();

        try {
            String decode = new URLDecoder().decode(path, "UTF-8");

            properties.load(new FileInputStream(decode));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            QueryRunner queryRunner = new QueryRunner(dataSource);
            List<Goods> query = queryRunner.query("select * from goods",
                    new BeanListHandler<>(Goods.class));
            for (Goods goods:query) {
                System.out.println(goods.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        insert();
    }
}
