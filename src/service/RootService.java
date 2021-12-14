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
				System.out.println("��ӳɹ���");
			}else {
				System.out.println("���ʧ��!");
			}
	}

	public void deleteBook(String bookname) throws Exception {
		int i = rs.deleteBook(bookname);
		if (i == 1) {
			System.out.println("ɾ���ɹ���");
		}else {
			System.out.println("δ�ҵ�����鼮!");
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
			System.out.println("�޸ĳɹ���");
		} else {
			System.out.println("�޸�ʧ�ܣ�");
		}
	}

}
