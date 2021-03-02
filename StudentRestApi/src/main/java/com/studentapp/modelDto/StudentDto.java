package com.studentapp.modelDto;

public class StudentDto {
	
	int stuId;
	String name;
	String dept;
	int age;
	
	
	
	public StudentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentDto(int stuId, String name, String dept, int age) {
		super();
		this.stuId = stuId;
		this.name = name;
		this.dept = dept;
		this.age = age;
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "StudentDto [stuId=" + stuId + ", name=" + name + ", dept=" + dept + ", age=" + age + "]";
	}
	

}
