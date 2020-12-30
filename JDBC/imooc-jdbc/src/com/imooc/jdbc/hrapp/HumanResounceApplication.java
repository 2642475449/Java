package com.imooc.jdbc.hrapp;

import com.imooc.jdbc.hrapp.command.Command;
import com.imooc.jdbc.hrapp.command.PstmtQueryCommend;

import java.util.Scanner;

public class HumanResounceApplication {
    public static void main(String[] args) {
        System.out.println("1-查询部门员工");
        Scanner in = new Scanner(System.in);

        Integer anInt = in.nextInt();

        switch (anInt) {
            case 1:
                Command command = new PstmtQueryCommend();
                command.execute();
                break;
        }

    }
}
