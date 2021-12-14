package service;

import dao.UserDao;
import pojo.User;

public class UserService {
	UserDao us = new UserDao();

	public void regist(String username, String password) throws Exception {
		int i = us.regist(username, password);
		if (i == 1) {
			System.out.println("注册成功！");
		} else if (i == 2) {
			System.out.println("该用户名已被注册");
		} else if (i == 3) {
			System.out.println("密码过长（请在8位内）");
		} else if (i == 4) {
			System.out.println("用户名过长（请在8位内）");
		} else {
			System.out.println("注册失败");
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
			return "root登陆成功";
		} else {
			System.out.println("登录成功！");
			return user.getUsername();
		}
	}

}
