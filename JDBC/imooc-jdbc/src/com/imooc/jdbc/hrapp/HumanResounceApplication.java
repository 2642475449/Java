package com.imooc.jdbc.hrapp;

import com.imooc.jdbc.hrapp.command.*;

import java.util.Scanner;

public class HumanResounceApplication {
    public static void main(String[] args) {
        System.out.println("1-查询部门员工");
        System.out.println("2-办理员工入职");
        System.out.println("3-调整员工薪资");
        System.out.println("4-办理员工离职");
        System.out.println("5-分页员工查询");
        Scanner in = new Scanner(System.in);
        Integer anInt = in.nextInt();
        Command command = null;

        switch (anInt) {
            case 1:
                command = new PstmtQueryCommand();
                command.execute();
                break;
            case 2:
                command = new InsertCommand();
                command.execute();
                break;
            case 3:
                command = new UpdateCommand();
                command.execute();
                break;
            case 4:
                command = new DeleteCommand();
                command.execute();
                break;
            case 5:
                command = new PaginationCommand();
                command.execute();
                break;
        }

    }
}
