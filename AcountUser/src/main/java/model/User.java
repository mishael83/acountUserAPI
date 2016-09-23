package model;

public class User {

	String userId;
	String userName;
	String userAcounId;
	
	public User() {
		super();
	}
	public User(String userId, String userName, String userAcounId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userAcounId = userAcounId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAcounId() {
		return userAcounId;
	}
	public void setUserAcounId(String userAcounId) {
		this.userAcounId = userAcounId;
	}
	
	
}
