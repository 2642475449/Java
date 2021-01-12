package com.imooc.jdbc.hrapp.command;

import com.imooc.jdbc.Common.DbUtils;
import com.imooc.jdbc.hrapp.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PaginationCommand implements Command {
    @Override
    public void execute() {
        System.out.println("请输入页码");
        Scanner scanner = new Scanner(System.in);
        Integer page = scanner.nextInt();
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Employee> list = new ArrayList<>();
        try {
            conn = DbUtils.getConnection();
            String sql = "SELECT * FROM employee LIMIT ? , 10;";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, (page - 1) * 10);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer eno = resultSet.getInt("eno");
                String ename = resultSet.getString("ename");
                float salary = resultSet.getFloat("salary");
                String dname = resultSet.getString("dname");
                Date hiredate = resultSet.getDate("hiredate");
                Employee emp = new Employee();
                emp.setEno(eno);
                emp.setEname(ename);
                emp.setSalary(salary);
                emp.setDname(dname);
                emp.setHiredate(hiredate);
                list.add(emp);
            }
            System.out.println(list.size());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            DbUtils.closeConnection(conn);
        }
    }
}
