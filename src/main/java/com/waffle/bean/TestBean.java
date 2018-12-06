package com.waffle.bean;

import java.io.Serializable;
import java.util.Date;

public class TestBean implements Serializable{
	private String name;
	private Integer age;
	private Date birth;

	public TestBean() {

	}

	public TestBean(String name, Integer age, Date birth) {
		super();
		this.name = name;
		this.age = age;
		this.birth = birth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "TestBean{" +
				"name='" + name + '\'' +
				", age=" + age +
				", birth=" + birth +
				'}';
	}


}
