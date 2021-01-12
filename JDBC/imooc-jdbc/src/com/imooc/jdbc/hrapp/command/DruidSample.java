package com.imooc.jdbc.hrapp.command;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.imooc.jdbc.Common.DbUtils;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * Druid连接池配置与使用
 */
public class DruidSample {
    public static void main(String[] args) throws UnsupportedEncodingException {


    //1.加载属性文件
        Properties properties = new Properties();
        String propertyFile = DruidSample.class.getResource("/druid-config.properties").getPath();
            propertyFile = new URLDecoder().decode(propertyFile,"UTF-8");



        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 加载配置文件
            properties.load(new FileInputStream(propertyFile));
            //2.获取DataSource数据源对象
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            //3.创建数据库连接
            conn = dataSource.getConnection();

            pstmt = conn.prepareStatement("select * from employee limit 0,10");
            rs = pstmt.executeQuery();
            while (rs.next()){
                Integer eno = rs.getInt(1);//表示把数据表的第一行提取出来
                String ename = rs.getString("ename");
                float salary = rs.getFloat("salary");
                String dname = rs.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(conn );
        }


    }
}
