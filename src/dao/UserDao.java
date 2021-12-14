package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import pojo.User;
import utils.JDBCDBCPUtils;

public class UserDao {
	QueryRunner qr = new QueryRunner(JDBCDBCPUtils.getDataSource());

	public int regist(String username, String password) throws Exception {
		String sql = "insert into User values(null, ?, ?, 50, null, null)";
		Object[] params = { username, password };
		User user = registFind(username);
		int i = 0;
		boolean usernameCheck = false;
		boolean passwordCheck = false;
		if (username.length() <= 8) {
			usernameCheck = true;
		}
		if (password.length() <= 8) {
			passwordCheck = true;
		}
		if (user == null && usernameCheck == true && passwordCheck == true) {
			i = qr.update(sql, params);
		} else if (usernameCheck != true) {
			i = 4;
		} else if (passwordCheck != true) {
			i = 3;
		} else if (user != null && user.getUsername().equals(username)){
			i = 2;
		}
		return i;
	}

	public User login(String username, String password) throws Exception {
		String sql = "select * from user where username = ? and password = ?";
		Object[] params = { username, password };
		User user = null;
		user = qr.query(sql, new BeanHandler<User>(User.class), params);
		// System.out.println(user);
		return user;
	}

	public User registFind(String username) throws Exception {
		String sql = "select * from user where username = ?";
		Object[] params = { username };
		User user = null;
		user = qr.query(sql, new BeanHandler<User>(User.class), params);
		// System.out.println(user);
		return user;
	}

}
