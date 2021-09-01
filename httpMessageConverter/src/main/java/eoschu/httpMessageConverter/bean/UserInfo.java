package eoschu.httpMessageConverter.bean;

public class UserInfo {

	private String account;
	private String password;
	private String email;
	private String number;
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "UserInfo [account=" + account + ", password=" + password + ", email=" + email + ", number=" + number
				+ "]";
	}
	public UserInfo(String account, String password, String email, String number) {
		super();
		this.account = account;
		this.password = password;
		this.email = email;
		this.number = number;
	}
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
