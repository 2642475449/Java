package com.imooc.jdbc.hrapp.sample;

import com.imooc.jdbc.Common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionSample {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DbUtils.getConnection();
            conn.setAutoCommit(true);//关闭自动提交
            String sql = "insert into employee(eno,ename,salary,dname) values(?,?,?,?)";
            for (int i = 1000; i < 2000; i++) {

                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, i);
                pstmt.setString(2, "员工" + i);
                pstmt.setFloat(3, 4000f);
                pstmt.setString(4, "市场部");
                pstmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null && !conn.isClosed())
                    conn.rollback();//回滚数据
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            DbUtils.closeConnection(conn);
        }
    }
}
