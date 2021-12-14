package view;

import java.util.Scanner;
import service.UserService;

public class UserView {

	UserService us = new UserService();

	public void run() throws Exception {
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		while (flag) {
			System.out.println("--------借书系统--------");
			System.out.println("1.登录： 2.注册：,3.退出：");
			System.out.println("请输入要操作的功能的序号[1-3]");
			String op = sc.next();
			switch (op) {
			case "1":
				login();
				break;
			case "2":
				regist();
				break;
			case "3":
				flag = false;
				System.out.println("退出成功!");
				break;
			default:
				System.out.println("输入错误，请重新输入");
			}
		}
	}

	private void regist() throws Exception {
		System.out.println("请输入用户名：(8位)");
		Scanner sc = new Scanner(System.in);
		String username = sc.next();
		System.out.println("请输入密码：(8位)");
		Scanner sc1 = new Scanner(System.in);
		String password = sc1.next();
		us.regist(username, password);
	}

	private void login() throws Exception {
		System.out.println("请输入用户名：");
		Scanner sc = new Scanner(System.in);
		String username = sc.next();
		System.out.println("请输入密码：");
		Scanner sc1 = new Scanner(System.in);
		String password = sc1.next();
		String login = us.login(username, password);
		if (login.equals("root登陆成功")) {
			System.out.println(login);
			new RootView().run();
		} else if (login.equals("")) {
			System.out.println("用户名密码错误请重新登陆:");
		} else {
			new LoginView().run(login);
		}

	}

}