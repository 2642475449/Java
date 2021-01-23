package com.command;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.news.News;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

/**
 * 查询新闻
 * 查询所有新闻列表
 */
public class QueryNews implements Command {



    @Override
    public  void execute() {
        DataSource dataSource = null;

        //读取配置文件
        Properties properties = new Properties();
        //得到配置文件路径
        String path = InsertNews.class.getResource("/druid-config.properties").getPath();
        try {
            //防止乱码
            String propertyFile = new URLDecoder().decode(path, "UTF-8");
            properties.load(new FileInputStream(propertyFile));
            // 获取DataSource数据源对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);
            QueryRunner queryRunner = new QueryRunner(dataSource);
            List<News> list = queryRunner.query("SELECT * FROM news", new BeanListHandler<>(News.class));

            for (News news:list){
                System.out.println(news.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
