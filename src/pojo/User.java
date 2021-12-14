	package pojo;

public class User {
	public User(int id, String username, String password, int money, String book, String time) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.money = money;
		this.book = book;
		this.time = time;
	}
	private int id;
	private String username;
	private String password;
	private int money;
	private String book;
	private String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", money=" + money + ", book="
				+ book + ", time=" + time + "]";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User() {
	}
}
