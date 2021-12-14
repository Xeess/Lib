package view;

import java.util.Scanner;

import service.RootService;
import service.UserService;

public class RootView {
	
	RootService rs = new RootService();

	public void run() throws Exception{
		boolean flag=true;
		Scanner sc = new Scanner(System.in);
		while (flag) {
			System.out.println("--------------管理员系统----------------");
			System.out.println("1.添加书籍：,2.删除书籍：,3.查询书籍：,4.全部查询：,5.修改书籍：,6.退出：");
			System.out.println("请输入要操作的功能的序号[1-6]");
			String op = sc.next();
			switch (op) {
			case "1":
				addBook();
				break;
			case "2":
				deleteBook();
				break;
			case "3":
				searchBook();
				break;
			case "4":
				allSearchBook();
				break;
			case "5":
				changeBook();
				break;
			case "6":
				flag = false;
				System.out.println("退出成功!");
				break;
			default:
				System.out.println("输入错误 请重新输入");
			}
		}
	}

	private void changeBook() {
		System.out.println("请输入要修改的字段名：");
		Scanner sc = new Scanner(System.in);
		String field = sc.next();
		System.out.println("请输入要修改的书名：");
		String bookName = sc.next();
		System.out.println("请输入数据：");
		String data = sc.next();
		rs.changeBook(field, bookName, data);
	}

	private void allSearchBook() throws Exception {
		System.out.println("查询结果：");
		rs.allSearchBook();
	}

	private void searchBook() throws Exception {
		System.out.println("请输入要查询的书籍：");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		rs.searchBook(bookname);
	}

	private void deleteBook() throws Exception {
		System.out.println("请输入要删除的书籍：");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		rs.deleteBook(bookname);
	}

	private void addBook() throws Exception {
		System.out.println("请输入要添加的书籍");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		rs.addBook(bookname);
	}

}
