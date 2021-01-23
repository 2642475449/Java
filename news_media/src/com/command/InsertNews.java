package com.command;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.news.News;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * 添加新闻：
 * 输入新闻标题，新闻内容，以及新闻日期 向数据库中添加新闻
 * @author xiaohang
 */
public class InsertNews implements Command {

    Date parse = null;
    Connection connection = null;

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入新闻标题");
        String title = scanner.next();
        System.out.println("请输入新闻内容");
        String content = scanner.next();
        System.out.println("请输入新闻日期，按年-月-日的形式输入");
        String data = scanner.next();

        Date parse = null;
        Connection connection = null;



        //读取配置文件
        Properties properties = new Properties();
        //得到配置文件路径
        String path = InsertNews.class.getResource("/druid-config.properties").getPath();
        try {
            //防止乱码
            String propertyFile = new URLDecoder().decode(path, "UTF-8");
            // 加载配置文件
            properties.load(new FileInputStream(propertyFile));
            // 获取DataSource数据源对象
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            // 建立连接
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            String sql1 = "INSERT INTO news(title,content,create_time) VALUES(?,?,?)";
            String sql2 = "SELECT * FROM news";
            QueryRunner queryRunner = new QueryRunner(dataSource);
            int update = queryRunner.update(connection, sql1, new Object[]{title, content, data});
//            List<News> list = queryRunner.query(sql2, new BeanListHandler<>(News.class));

            if (update!=0){
                System.out.println("提交完成");
                connection.commit();
            } else {
                System.out.println("提交失败");
            }
//            for (News news:list){
//                System.out.println(news.toString());
//            }

        } catch (Exception e) {
            try {
                if (connection !=null && !connection.isClosed()) {

                    connection.rollback();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try{
              connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throwables.printStackTrace();
            }
        }











    }
}
