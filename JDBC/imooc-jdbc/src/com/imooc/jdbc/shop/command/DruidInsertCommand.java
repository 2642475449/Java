package com.imooc.jdbc.shop.command;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.imooc.jdbc.Common.DbUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * 阿里巴巴druid连接池的使用：
 * 第一步，导入druid的包，并进行加载
 * 第二步配置config.properties文件
 * 第三步在连接池中配置使用Druid：
 */
public class DruidInsertCommand  {

    public static void main(String[] args) {
        //加载配置文件
        Properties properties = new Properties();
        //获取根路径下的配置文件
        String path = DruidInsertCommand.class.getResource("/druid-config.properties").getPath();


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //防止乱码
            String decode = new URLDecoder().decode(path, "UTF-8");
            //加载配置文件
            properties.load(new FileInputStream(decode));
            //获取DataSource数据源对象
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            //建立连接
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM goods");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                float price = resultSet.getFloat("price");
                String desp = resultSet.getString("desp");
                Date create_time = resultSet.getDate("create_time");
                System.out.println(id + " " + name + " " + price + " " + desp + " " + create_time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(connection);
        }
    }
}
