package com.command;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.news.News;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/**
 * 修改新闻
 * 先查询所有的新闻列表，根据新闻列表中的id修改新闻标题，内容
 */
public class UpdateNews implements Command {

    Scanner  scanner = new Scanner(System.in);
    QueryRunner queryRunner = null;
    Connection connection = null;
    Properties properties = null;


    @Override
    public void execute() {
        //输入提示与配置
        System.out.println("请输入要修改的新闻id");
        Integer id = scanner.nextInt();

        try {
            properties = new Properties();//读取配置文件
            String path = UpdateNews.class.getResource("/druid-config.properties").getPath();//得到配置文件路径
            String propertyFile = new URLDecoder().decode(path, "UTF-8");//防止乱码
            properties.load(new FileInputStream(propertyFile));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);// 获取DataSource数据源对象
            connection = dataSource.getConnection();//创建连接
            connection.setAutoCommit(false);//设置为手动提交
            queryRunner = new QueryRunner(dataSource);


            //通过id查询
            String queryNews = "SELECT * FROM news WHERE id = ?";
            List<News> list = queryRunner.query(queryNews, new BeanListHandler<>(News.class),new Object[]{id});
            //输出id查询
            System.out.println(list.toString());

            System.out.println("请输入要修改的新闻标题");
            String title = scanner.next();
            System.out.println("请输入要修改的新闻内容");
            String content = scanner.next();
            //通过id修改
            String updateNews = "UPDATE news SET title=? , content= ? WHERE id = ?";
            int update = queryRunner.update(connection, updateNews, new Object[]{title, content, id});
            if (update == 1){
                System.out.println("修改成功");
                connection.commit();
            } else {
                System.out.println("修改失败");
            }


        } catch (Exception e) {
            //回滚
            try {
                if (connection !=null && !connection.isClosed()) {
                    connection.rollback();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            //关闭连接
            try {
                if (connection !=null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
