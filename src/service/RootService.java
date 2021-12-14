package service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.RootDao;
import pojo.ABook;

public class RootService {
	
	RootDao rs = new RootDao();

	public void addBook(String bookname) throws Exception {
			int i = rs.addBook(bookname);
			if (i == 1) {
				System.out.println("添加成功！");
			}else {
				System.out.println("添加失败!");
			}
	}

	public void deleteBook(String bookname) throws Exception {
		int i = rs.deleteBook(bookname);
		if (i == 1) {
			System.out.println("删除成功！");
		}else {
			System.out.println("未找到相关书籍!");
		}
	}

	public void searchBook(String bookname) throws Exception {
		rs.searchBook(bookname);
	}

	public void allSearchBook() throws Exception {
		rs.allSearchBook();
	}

	public void changeBook(String field, String bookName, String data) {
		int i = rs.changeBook(field, bookName, data);
		if (i == 1) {
			System.out.println("修改成功！");
		} else {
			System.out.println("修改失败！");
		}
	}

}
