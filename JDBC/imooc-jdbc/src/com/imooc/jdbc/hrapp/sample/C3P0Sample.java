package com.imooc.jdbc.hrapp.sample;

import com.imooc.jdbc.Common.DbUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * C3P0连接池的使用
 * 1.加载配置文件
 * 2.创建DataSource
 *  3.得到数据库连接
 */
public class C3P0Sample {
    public static void main(String[] args) {
        DataSource dataSource = new ComboPooledDataSource();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            conn = dataSource.getConnection();
            pstmt = conn.prepareStatement("select  * from  employee limit 0,10");
            resultSet = pstmt.executeQuery();

            while (resultSet.next()){
                Integer eno = resultSet.getInt(1);//表示把数据表的第一行提取出来
                String ename = resultSet.getString("ename");
                float salary = resultSet.getFloat("salary");
                String dname = resultSet.getString("dname");
                System.out.println(dname + "-" + eno + "-" + ename + "-" + salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(conn );
        }
    }
}
