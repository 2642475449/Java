package com.imooc.jdbc.shop;

import com.imooc.jdbc.shop.command.*;

import java.util.Scanner;

public class ShopApplication {
    public static void main(String[] args) {
        System.out.println("1-查询商品");
        System.out.println("2-添加商品");
        System.out.println("3-修改商品");
        System.out.println("5-查询商品");
        System.out.println("");

        Scanner scanner = new Scanner(System.in);
        Integer number = scanner.nextInt();
        Command command = null;
        switch (number) {
            case 1:
                command = new QueryCommand();
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


        }

    }
}
