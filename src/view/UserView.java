package view;

import java.util.Scanner;
import service.UserService;

public class UserView {

	UserService us = new UserService();

	public void run() throws Exception {
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		while (flag) {
			System.out.println("--------����ϵͳ--------");
			System.out.println("1.��¼�� 2.ע�᣺,3.�˳���");
			System.out.println("������Ҫ�����Ĺ��ܵ����[1-3]");
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
				System.out.println("�˳��ɹ�!");
				break;
			default:
				System.out.println("�����������������");
			}
		}
	}

	private void regist() throws Exception {
		System.out.println("�������û�����(8λ)");
		Scanner sc = new Scanner(System.in);
		String username = sc.next();
		System.out.println("���������룺(8λ)");
		Scanner sc1 = new Scanner(System.in);
		String password = sc1.next();
		us.regist(username, password);
	}

	private void login() throws Exception {
		System.out.println("�������û�����");
		Scanner sc = new Scanner(System.in);
		String username = sc.next();
		System.out.println("���������룺");
		Scanner sc1 = new Scanner(System.in);
		String password = sc1.next();
		String login = us.login(username, password);
		if (login.equals("root��½�ɹ�")) {
			System.out.println(login);
			new RootView().run();
		} else if (login.equals("")) {
			System.out.println("�û���������������µ�½:");
		} else {
			new LoginView().run(login);
		}

	}

}