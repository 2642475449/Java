package com.imooc.jdbc.hrapp.sample;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.imooc.jdbc.hrapp.entity.Employee;
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
 * Apache DBUtils + Druid联合使用
 */
public class DbutilsSample {

    private static void query() {
        //创建读取配置文件
        Properties properties = new Properties();
        //得到配置文件路径
        String path = DbutilsSample.class.getResource("/druid-config.properties").getPath();
        try {
            //防止乱码
            String propertyFile = new URLDecoder().decode(path, "UTF-8");
            //载入文文件
            properties.load(new FileInputStream(propertyFile));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            //利用Apache DbUtils大幅简化了数据的提取过程
            QueryRunner queryRunner = new QueryRunner(dataSource);
            List<Employee> list = queryRunner.query("select * from employee limit ?,10",
                    new BeanListHandler<>(Employee.class),
                    new Object[]{0});
            for (Employee emp:list) {
                System.out.println(emp.getEname());
            }
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update() {
        //创建读取配置文件
        Properties properties = new Properties();
        //得到配置文件路径
        String path = DbutilsSample.class.getResource("/druid-config.properties").getPath();
        Connection connection = null;
        try {
            //防止乱码
            String propertyFile = new URLDecoder().decode(path, "UTF-8");
            //载入文文件
            properties.load(new FileInputStream(propertyFile));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            connection = dataSource.getConnection();
            //设为手动提交
            connection.setAutoCommit(false);
            String sql1="update employee set salary=salary+1000 where eno=?";
            String sql2="update employee set salary=salary-500 where eno=?";
            QueryRunner queryRunner = new QueryRunner();
            queryRunner.update(connection,sql1,new Object[]{100});
            queryRunner.update(connection,sql2,new Object[]{3610});
            connection.commit();
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
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //查询
        query();
        //修改
        update();

    }
}
