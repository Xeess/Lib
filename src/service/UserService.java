package service;

import dao.UserDao;
import pojo.User;

public class UserService {
	UserDao us = new UserDao();

	public void regist(String username, String password) throws Exception {
		int i = us.regist(username, password);
		if (i == 1) {
			System.out.println("ע��ɹ���");
		} else if (i == 2) {
			System.out.println("���û����ѱ�ע��");
		} else if (i == 3) {
			System.out.println("�������������8λ�ڣ�");
		} else if (i == 4) {
			System.out.println("�û�������������8λ�ڣ�");
		} else {
			System.out.println("ע��ʧ��");
		}
	}

	public String login(String username, String password) throws Exception {
		if (username.equals("") || username == null) {
			return "";
		}
		User user = us.login(username, password);
		if (user == null) {
			return "";
		} else if (user.getUsername().equals("root")) {
			return "root��½�ɹ�";
		} else {
			System.out.println("��¼�ɹ���");
			return user.getUsername();
		}
	}

}
