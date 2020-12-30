package com.imooc.jdbc.shop;

import com.imooc.jdbc.shop.command.Command;
import com.imooc.jdbc.shop.command.QueryCommend;

import java.util.Scanner;

public class ShopApplication {
    public static void main(String[] args) {
        System.out.println("1-查询电器");
        Scanner scanner = new Scanner(System.in);
        Integer number = scanner.nextInt();
        switch (number) {
            case 1:
                QueryCommend command = new QueryCommend();
                command.execute();
        }

    }
}
