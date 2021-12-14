package pojo;

public class ABook {
	public ABook(String bookname, int book_id, int money, int status, String user, String time) {
		super();
		this.bookname = bookname;
		this.book_id = book_id;
		this.money = money;
		this.status = status;
		this.user = user;
		this.time = time;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public ABook() {
		
	}
	private String bookname;
	private int book_id;
	private int money;
	private int status;
	private String user;
	private String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	@Override
	public String toString() {
		return "ABook [bookname=" + bookname + ", book_id=" + book_id + ", money=" + money + ", status=" + status
				+ ", user=" + user + ", time=" + time + "]";
	}
	
}
