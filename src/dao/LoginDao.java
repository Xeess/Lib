package dao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.management.Query;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import pojo.ABook;
import pojo.User;
import utils.JDBCDBCPUtils;

public class LoginDao {

	QueryRunner qr = new QueryRunner(JDBCDBCPUtils.getDataSource());

	public void findAllBook() {
		String sql = "select * from ABook";
		Object[] params = {};
		List<ABook> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<ABook>(ABook.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (ABook abook : list) {
			System.out.println(abook);
		}
	}

	public void pagingSearch(String page) {
		String sql = "select * from ABook limit ?,3";
		Object[] params = { (Integer.parseInt(page) - 1) * 3 };
		long row = pagingSearchAll();
		// pagingSearchAll();
		List<ABook> list = null;
		if ((Integer.parseInt(page) - 1) * 3 < row && Integer.parseInt(page) * 3 > 0) {
			try {
				list = qr.query(sql, new BeanListHandler<ABook>(ABook.class), params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			for (ABook abook : list) {
				System.out.println(abook);
			}
		} else {
			System.out.println("页数错误");
		}
	}

	private long pagingSearchAll() {
		String sql = "select count(*) from ABook";
		Long query = 0L;
		try {
			query = qr.query(sql, new ScalarHandler<Long>());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// System.out.println(query);
		return query;
	}

	public void searchBook(String bookname) {
		String sql = "select * from ABook where bookname like ?";
		Object[] params = { "%" + bookname + "%" };
		List<ABook> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<ABook>(ABook.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		boolean flag = false;
		for (ABook abook : list) {
			System.out.println(abook);
			flag = true;
		}
		if (flag == false) {
			System.out.println("未找到相关书籍");
		}

	}

	public int borrowBook(String login, String bookname) {
		String sql1 = "update user set money = money - 10, book = ?,time = date_add(curdate(), interval 10 day) where username = ?";
		String sql2 = "update abook set status = 1, user = ?,time = date_add(curdate(), interval 10 day) where bookname = ? and status = 2";
		String sql3 = "select * from abook where bookname = ?";
		String sql4 = "select * from user where username = ?";
		Object[] params1 = { bookname, login };
		Object[] params2 = { login, bookname };
		Object[] params3 = { bookname };
		Object[] params4 = { login };
		int i = 0;
		try {
			if (qr.query(sql4, new BeanHandler<User>(User.class), params4).getMoney() > 10) {
				if (qr.query(sql3, new BeanHandler<ABook>(ABook.class), params3) != null) {
					if (qr.query(sql3, new BeanHandler<ABook>(ABook.class), params3).getStatus() == 1) {
						System.out.println("该书已被借了");
					}
					i = qr.update(sql2, params2);
					if (i == 1) {
						qr.update(sql1, params1);
					}
				} else {
					System.out.println("未找到该书籍");
				}
			} else {
				System.out.println("没钱了");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public void lookMyBook(String login) {
		String sql = "select * from ABook where user = ?";
		Object[] params = { login };
		List<ABook> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<ABook>(ABook.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (ABook abook : list) {
			System.out.println(abook);
		}
		if (list.isEmpty()) {
			System.out.println("还尚未借书");
		}
	}

	public int returanBook(String login, String bookname) {
		String sql1 = "update abook set time = null, user = null, status = 2 where user = ? and bookname = ?";
		String sql2 = "SELECT DATEDIFF(CURDATE(), time) from ABook where user = ? and bookname = ?";
		Object[] params1 = { login, bookname };
		int i = 0;
		try {
			Long query = 0L;
			query = qr.query(sql2, new ScalarHandler<Long>(), params1);
			if (query != null) {
				String sql3 = "update user set money = money - ? where username = ?";
				Object[] params3 = { query, login };
				qr.update(sql3, params3);
				System.out.println("超时" + query + "天");
			}
			i = qr.update(sql1, params1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public int commentBook(String login, String bookname, String comment) {
		int i = 0;
		String sql = "insert into comment values(?, ?, ?, null, null)";
		Object[] params = { bookname, login, comment };
		ABook bn = search_Book(bookname);
		if (bn != null) {
			try {
				i = qr.update(sql, params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("未找到书籍");
		}
		// if (bookname.equals("python")) {
		// String sql = "insert into python values(?, ?, null, null)";
		// Object[] params = { login, comment };
		// try {
		// i = qr.update(sql, params);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// } else if (bookname.equals("html")) {
		// String sql = "insert into python values(?, ?, null)";
		// Object[] params = { login, comment };
		// try {
		// i = qr.update(sql, params);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// } else {
		// System.out.println("未找到书籍");
		// }
		return i;
	}

	private ABook search_Book(String bookname) {
		String sql = "select bookname from abook where bookname = ?";
		Object[] params = { bookname };
		ABook query = null;
		try {
			query = qr.query(sql, new BeanHandler<>(ABook.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return query;
	}

	public void lookComment(String bookname) {
		String sql = "select * from comment where bookname = ?";
		Object[] params = { bookname };
		List<Object[]> list = null;
		ABook bn = search_Book(bookname);
		if (bn != null) {
			try {
				list = qr.query(sql, new ArrayListHandler(), params);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("[书籍       原评论者       评论       二级评论者       评论]");
			for (Object[] objects : list) {
				System.out.println(Arrays.toString(objects));
			}
		} else {
			System.out.println("未找到该书籍");
		}
		// if (bookname.equals("python")) {
		// String sql = "select * from python order by user";
		// Object[] params = {};
		// List<Object[]> list = null;
		// try {
		// list = qr.query(sql, new ArrayListHandler(), params);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// System.out.println("原评论者 评论 二级评论者 评论");
		// for (Object[] objects : list) {
		// System.out.println(Arrays.toString(objects));
		// }
		// } else if (bookname.equals("html")) {
		// String sql = "select * from html order by user";
		// Object[] params = {};
		// List<Object[]> list = null;
		// try {
		// list = qr.query(sql, new ArrayListHandler(), params);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// System.out.println("原评论者 评论 二级评论者 评论");
		// for (Object[] objects : list) {
		// System.out.println(Arrays.toString(objects));
		// }
		// } else {
		// System.out.println("未找到书籍");
		// }
	}

	public int secondCommentBook(String login, String bookname, String secondComment, String oldUser, String oldComment) {	
		int i = 0;
		ABook bn = search_Book(bookname);
		if (bn != null) {
			Object[] user = searchUser(oldUser, oldComment);
			if (user.length != 0) {
				String sql = "insert into comment values(?, ?, ?, ?, ?)";
				Object[] params = { bookname, oldUser, oldComment, login, secondComment };
				try {
					i = qr.update(sql, params);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("未找到该评论者或该评论");
			}
		} else {
			System.out.println("未找到该书籍");
		}
		// if (bookname.equals("python")) {
		// Object[] user = searchUser(oldUser);
		// String sql = "insert into python values(?, ?, ?, ?)";
		// Object[] params = { oldUser, user[1], login, secondComment };
		// try {
		// i = qr.update(sql, params);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// } else if (bookname.equals("html")) {
		// Object[] user = searchUser(oldUser);
		// String sql = "insert into python values(?, ?, ?, ?)";
		// Object[] params = { oldUser, user[1], login, secondComment };
		// try {
		// i = qr.update(sql, params);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// } else {
		// System.out.println("未找到书籍或评论");
		// }
		return i;
	}

	private Object[] searchUser(String oldUser, String oldComment) {
		String sql = "select user, comment from comment where user = ? and comment = ?";
		Object[] params = { oldUser, oldComment };
		Object[] query = null;
		try {
			query = qr.query(sql, new ArrayHandler(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return query;
	}
}

// private boolean bookTableExist(String bookname) {
// String sql = ("select * from (SELECT TABLE_NAME FROM
// INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA= 'jieshu') as tab where
// TABLE_NAME = ?");
// Object[] params = { bookname };
// boolean flag = false;
// try {
// Object[] query = qr.query(sql, new ArrayHandler());
// if (query == null) {
// flag = false;
// }else {
// flag = true;
// }
// } catch (SQLException e) {
// e.printStackTrace();
// }
// return flag;
// }
