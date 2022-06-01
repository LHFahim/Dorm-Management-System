package com.fahims.dormportal;

public class Student {
	private int id;
	private String firstName;
	private String lastName;
	private String gender;
	private String major;
	private String sid;
	private String passport;
	private String room;
	private String dob;
	private String phone;

	
	public Student(int id, String firstName, String lastName, String gender, String major, String sid, String passport,
			String room, String dob, String phone) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.major = major;
		this.sid = sid;
		this.passport = passport;
		this.room = room;
		this.dob = dob;
		this.phone = phone;
	}
	
	
	

	public Student(String firstName, String lastName, String gender, String major, String sid, String passport,
			String room, String dob, String phone) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.major = major;
		this.sid = sid;
		this.passport = passport;
		this.room = room;
		this.dob = dob;
		this.phone = phone;
	}
	
	




	public Student(int id, String firstName, String lastName, String room, String phone) {
//		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.room = room;
		this.phone = phone;
	}




	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", major=" + major + ", sid=" + sid + ", passport=" + passport + ", room=" + room + ", dob=" + dob
				+ ", phone=" + phone + "]";
	}

	
}
