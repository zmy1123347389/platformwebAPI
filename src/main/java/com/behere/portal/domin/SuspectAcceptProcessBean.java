package com.behere.portal.domin;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
public class SuspectAcceptProcessBean implements Serializable {
	private static final long serialVersionUID = -5808854497229848967L;
	
	//姓名
	private String name;
	
	//性别
	private String sex;
	
	//民族
	private String nation;
	
	//出生日期
	private String birthday;
	
	//住址
	private String address;
	
	//身份证号
	private String cardNum;
	
	//编号
	private String number;
	
	//受理流程
	private List<Map<String, String>> acceptPro;

	public SuspectAcceptProcessBean() {}

	public SuspectAcceptProcessBean(String name, String sex, String nation,
			String birthday, String address, String cardNum, String number,
			List<Map<String, String>> acceptPro) {
		super();
		this.name = name;
		this.sex = sex;
		this.nation = nation;
		this.birthday = birthday;
		this.address = address;
		this.cardNum = cardNum;
		this.number = number;
		this.acceptPro = acceptPro;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public List<Map<String, String>> getAcceptPro() {
		return acceptPro;
	}

	public void setAcceptPro(List<Map<String, String>> acceptPro) {
		this.acceptPro = acceptPro;
	}
}