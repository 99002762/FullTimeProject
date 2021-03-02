package com.studentapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="students")
public class Student {
	
	@Id
	int stuId;
	String name;
	String dept;
	int age;
	
	
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int stuId, String name, String dept, int age) {
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
		return "Student [stuId=" + stuId + ", name=" + name + ", dept=" + dept + ", age=" + age + "]";
	}
	
	
	
//	Address address;
	
	
   

}
