package view;

import java.util.Scanner;

import service.LoginService;

public class LoginView {

	LoginService lv = new LoginService();

	public void run(String login) {
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		while (flag) {
			System.out.println("--------------����ϵͳ----------------");
			System.out.println("1.��ѯ�����鼮��,2.��ҳ��ѯ��,3.��ѯ�鼮��,4.����:,5.Ŀǰ����飺,6.����:,7.�����鼮��,8.�������ۣ�,9.��ѯ�鼮����:,10.�˳�:");
			System.out.println("������Ҫ�����Ĺ��ܵ����[1-10]");
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
				System.out.println("�˳��ɹ���");
				break;
			default:
				System.out.println("������� ����������");
			}
		}
	}

	private void lookComment() {
		System.out.println("����������鿴���鼮��");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		lv.lookComment(bookname);
	}

	private void secondCommentBook(String login) {
		System.out.println("�����������۵��鼮��");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		System.out.println("�������������۵��ˣ�");
		String oldUser = sc.next();
		System.out.println("�������������۵����ۣ�");
		String oldComment = sc.next();
		System.out.println("������������ۣ�");
		String secondComment = sc.next();
		lv.secondCommentBook(login, bookname, secondComment, oldUser, oldComment);

	}

	private void commentBook(String login) {
		System.out.println("�����������۵��鼮��");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		System.out.println("������������ۣ�");
		String comment = sc.next();
		lv.commentBook(login, bookname, comment);
	}

	private void returnBook(String login) {
		System.out.println("��������Ҫ�����鼮��");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		lv.returnBook(login, bookname);
	}

	private void lookMyBook(String login) {
		lv.lookMyBook(login);
	}

	private void borrowBook(String login) {
		System.out.println("��������Ҫ����鼮��");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		lv.borrowBook(login, bookname);
	}

	private void searchBook() {
		System.out.println("������Ҫ��ѯ���鼮��");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		lv.searchBook(bookname);
	}

	private void pagingSearch() {
		System.out.println("������Ҫ�鿴��ҳ����һҳ�������ݣ�:");
		Scanner sc = new Scanner(System.in);
		String page = sc.next();
		lv.pagingSearch(page);
	}

	private void findAllBook() {
		System.out.println("��ѯ�����");
		lv.findAllBook();
	}
}
