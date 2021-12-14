package service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.LoginDao;

public class LoginService {

	LoginDao ld = new LoginDao();

	public void findAllBook() {
		ld.findAllBook();
	}

	public void pagingSearch(String page) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(page);
		if (!isNum.matches()) {
			System.out.println("请输入数字");
		} else {
			ld.pagingSearch(page);
		}
	}

	public void searchBook(String bookname) {
		ld.searchBook(bookname);
	}

	public void borrowBook(String login, String bookname) {
		int i = ld.borrowBook(login, bookname);
		if (i == 1) {
			System.out.println("借书成功");
		} else {
			System.out.println("借书失败");
		}
	}

	public void lookMyBook(String login) {
		ld.lookMyBook(login);
	}

	public void returnBook(String login, String bookname) {
		int i = ld.returanBook(login, bookname);
		if (i == 1) {
			System.out.println("还书成功");
		}else {
			System.out.println("还书失败");
		}
	}

	public void commentBook(String login, String bookname, String comment) {
		int i = ld.commentBook(login, bookname, comment);
		if (i == 1) {
			System.out.println("评论成功");
		}else {
			System.out.println("评论失败");
		}
	}

	public void lookComment(String bookname) {
		ld.lookComment(bookname);
	}

	public void secondCommentBook(String login, String bookname, String secondComment, String oldUser, String oldComment) {
		int i = ld.secondCommentBook(login, bookname, secondComment, oldUser, oldComment);
		if (i == 1) {
			System.out.println("评论成功");
		}else {
			System.out.println("评论失败");
		}
	}
}
