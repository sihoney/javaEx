package com.javaex.helloworld;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelloWorld {
	public static void main(String[] args) {
		
	}
}

public class A implements Cloneable {

	private String name;
	
	private ArrayList<String> list = new ArrayList<String>();

	public List getList(){
		return list;
	}

	public void setList(String value) {
		this.list.add(value);
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Object clone() throws CloneNotSupportedException {
		A a = (A) super.clone();
		
		a.list = (ArrayList)list.clone();
		
		return a;
	}

}