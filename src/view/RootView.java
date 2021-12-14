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
			System.out.println("--------------����Աϵͳ----------------");
			System.out.println("1.����鼮��,2.ɾ���鼮��,3.��ѯ�鼮��,4.ȫ����ѯ��,5.�޸��鼮��,6.�˳���");
			System.out.println("������Ҫ�����Ĺ��ܵ����[1-6]");
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
				System.out.println("�˳��ɹ�!");
				break;
			default:
				System.out.println("������� ����������");
			}
		}
	}

	private void changeBook() {
		System.out.println("������Ҫ�޸ĵ��ֶ�����");
		Scanner sc = new Scanner(System.in);
		String field = sc.next();
		System.out.println("������Ҫ�޸ĵ�������");
		String bookName = sc.next();
		System.out.println("���������ݣ�");
		String data = sc.next();
		rs.changeBook(field, bookName, data);
	}

	private void allSearchBook() throws Exception {
		System.out.println("��ѯ�����");
		rs.allSearchBook();
	}

	private void searchBook() throws Exception {
		System.out.println("������Ҫ��ѯ���鼮��");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		rs.searchBook(bookname);
	}

	private void deleteBook() throws Exception {
		System.out.println("������Ҫɾ�����鼮��");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		rs.deleteBook(bookname);
	}

	private void addBook() throws Exception {
		System.out.println("������Ҫ��ӵ��鼮");
		Scanner sc = new Scanner(System.in);
		String bookname = sc.next();
		rs.addBook(bookname);
	}

}
