package view;

import java.util.Scanner;

import service.LoginService;

public class LoginView {

	LoginService lv = new LoginService();

	public void run(String login) {
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		while (flag) {
			System.out.println("--------------借书系统----------------");
			System.out.println("1.查询所有书籍：,2.分页查询：,3.查询书籍：,4.借书:,5.目前借的书：,6.还书:,7.评论书籍：,8.二级评论：,9.查询书籍评论:,10.退出:");
			System.out.println("请输入要操作的功能的序号[1-10]");
			String op = sc.next();
			switch (op) {
			case "1":
				findAllBook();
				break;
			case "2":
				pagingSearch();
				break;
			case "3":
				searchBook();
				break;
			case "4":
				borrowBook(login);
				break;
			case "5":
				lookMyBook(login);
				break;
			case "6":
				returnBook(login);
				break;
			case "7":
				commentBook(login);
				break;
			case "8":
				secondCommentBook(login);
				break;
			case "9":
				lookComment();
				break;
			case "10":
				flag = false;
				System.out.println("退出成功！");
				break;
			default:
				System.out.println("输入错误 请重新输入");
			}
		}
	}

	private void lookComment() {
		System.out.println("请输入你想查看的书籍：");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		lv.lookComment(bookname);
	}

	private void secondCommentBook(String login) {
		System.out.println("请输入你评价的书籍：");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		System.out.println("请输入你想评价的人：");
		String oldUser = sc.next();
		System.out.println("请输入你想评价的评价：");
		String oldComment = sc.next();
		System.out.println("请输入你的评价：");
		String secondComment = sc.next();
		lv.secondCommentBook(login, bookname, secondComment, oldUser, oldComment);

	}

	private void commentBook(String login) {
		System.out.println("请输入你评价的书籍：");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		System.out.println("请输入你的评价：");
		String comment = sc.next();
		lv.commentBook(login, bookname, comment);
	}

	private void returnBook(String login) {
		System.out.println("请输入你要还的书籍：");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		lv.returnBook(login, bookname);
	}

	private void lookMyBook(String login) {
		lv.lookMyBook(login);
	}

	private void borrowBook(String login) {
		System.out.println("请输入你要借的书籍：");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		lv.borrowBook(login, bookname);
	}

	private void searchBook() {
		System.out.println("请输入要查询的书籍：");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		lv.searchBook(bookname);
	}

	private void pagingSearch() {
		System.out.println("请输入要查看的页数（一页三条数据）:");
		Scanner sc = new Scanner(System.in);
		String page = sc.next();
		lv.pagingSearch(page);
	}

	private void findAllBook() {
		System.out.println("查询结果：");
		lv.findAllBook();
	}
}
