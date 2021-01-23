package com.news;

import com.command.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 新闻主界面
 */
public class NewsSelection {
    public static void main(String[] args){
        Command command = null;

        System.out.println("欢迎来到新闻管理系统");
        System.out.println("==================");
        System.out.println("   1 添加新闻\n" +
                            "   2 查看新闻\n" +
                            "   3 编辑新闻\n" +
                            "   4 删除新闻\n" +
                            "   5 退出系统");
        System.out.println("输入1-5之间的数字");

        //while循环判断值
        boolean n=true;
        while (n){
            try {
                //键盘输入
                Scanner scanner = new Scanner(System.in);
                Integer i = scanner.nextInt();
                switch (i) {
                    case 1:
                        command = new InsertNews();
                        command.execute();
                        command = new QueryNews();
                        command.execute();
                        n=false;
                        break;
                    case 2:
                        System.out.println("新闻列表如下:");
                        command = new QueryNews();
                        command.execute();
                        n=false;
                        break;
                    case 3:
                        command = new QueryNews();
                        command.execute();
                        command = new UpdateNews();
                        command.execute();

                        n=false;
                        break;
                    case 4:
                        command = new DeleteNews();
                        command.execute();
                        n=false;
                        break;
                    case 5:
                        n=false;
                        break;
                    default:
                        System.out.println("输入错误,请重新输入1-5之间的数字");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("输入错误,请重新输入1-5之间的数字");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
