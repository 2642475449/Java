package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.Common.DbUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 新增员工数据
 */
public class InsertCommand implements Command {
    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入员工编号：");
        int eno = scanner.nextInt();
        System.out.println("请输入员工姓名：");
        String ename = scanner.next();
        System.out.println("请输入员工薪资：");
        float salary = scanner.nextFloat();
        System.out.println("请输入隶属部门：");
        String dname = scanner.next();
        System.out.println("请输入职日期");
        String strHiredate = scanner.next();

        java.util.Date udHiredate = null;

        //1.String转化java.util.Date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
             udHiredate = sdf.parse(strHiredate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //2.java.util.Date转为java.sql.Date
        long time = udHiredate.getTime();//获取自1970年到现在的毫秒数\
        java.sql.Date sdHiredate = new java.sql.Date(time);
        PreparedStatement statement = null;
        Connection conn = null;

        try {
             conn = DbUtils.getConnection();
            String sql = "insert into employee(eno,ename,salary,dname) values(?,?,?,?)";
            statement = conn.prepareStatement(sql);
            statement.setInt(1,eno);
            statement.setString(2,ename);
            statement.setFloat(3,salary);
            statement.setString(4,dname);
            int cnt = statement.executeUpdate();
            System.out.println("cnt：" + cnt);
            System.out.println(ename);
            System.out.println(ename + "员工入职手续已办理");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(conn);
        }

    }
}
