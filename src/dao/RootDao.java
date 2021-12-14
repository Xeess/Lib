package dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import pojo.ABook;
import utils.JDBCDBCPUtils;

public class RootDao {
	QueryRunner qr = new QueryRunner(JDBCDBCPUtils.getDataSource());

	public int addBook(String bookname) throws Exception {
		String sql = "insert into ABook values(?, null, 10, 2, null, null)";
		Object[] params = { bookname };
		int i = qr.update(sql, params);
		return i;
	}

	public int deleteBook(String bookname) throws Exception {
		String sql = "delete from ABook where bookname = ?";
		Object[] params = { bookname };
		int i = qr.update(sql, params);
		return i;
	}

	public void searchBook(String bookname) throws Exception {
		String sql = "select * from ABook where bookname like ?";
		Object[] params = { "%" + bookname + "%" };
		// String sql = "select * from ABook where bookname = ?";
		// Object[] params = { bookname };
		// ABook abook = null;
		// abook = qr.query(sql, new BeanHandler<ABook>(ABook.class), params);
		// System.out.println(abook);
		List<ABook> list = qr.query(sql, new BeanListHandler<ABook>(ABook.class), params);
		boolean flag = false;
		for (ABook abook : list) {
			System.out.println(abook);
			flag = true;
		}
		if (flag == false) {
			System.out.println("未找到相关书籍");
		}
	}

	public void allSearchBook() throws Exception {
		String sql = "select * from ABook";
		Object[] params = {};
		List<ABook> list = qr.query(sql, new BeanListHandler<ABook>(ABook.class), params);
		for (ABook abook : list) {
			System.out.println(abook);
		}
	}

	public int changeBook(String field, String bookName, String data) {
		String sql = null;
		int i = 0;
		if (field.equals("bookname")) {
			sql = "update ABook set bookname = ? where bookname = ?";
			Object[] params = { data, bookName };
			try {
				i = qr.update(sql, params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (field.equals("status")) {
			sql = "update ABook set status = ? where bookname = ?";
			Object[] params = { Integer.parseInt(data), bookName };
			try {
				i = qr.update(sql, params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}

}
