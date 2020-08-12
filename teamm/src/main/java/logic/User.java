package logic;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class User {
	private int uno;
	@Size(min=2, max=10, message="2자 이상 10자 이하입니다.")
	private String name;
	@Size(min=4, max=10, message="4자 이상 10자 이하입니다.")
	private String id;
	@Size(min=4, message="4자 이상입니다.")
	private String password;
	private String introduction;
	@Email(message="Email 형식으로 입력하세요.")
	private String email;
	private Date regdate;
	
	
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public int getUno() {
		return uno;
	}
	public void setUno(int uno) {
		this.uno = uno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "User [uno=" + uno + ", name=" + name + ", id=" + id + ", password=" + password + ", introduction="
				+ introduction + ", email=" + email + ", regdate=" + regdate + "]";
	}
	

	
}
