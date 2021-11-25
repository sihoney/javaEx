package com.javaex.helloworld;

//Abstraction shows only the essential attributes 
//and hides unnecessary details of the object from the users.
//abstract class, abstract methods and interfaces.

//Abstract java class cannot be instantiated.
public class Abstraction {
 public static void main(String[] args){
     Shape obj = new guru99();
     
     obj.calculateArea();
 }
}

abstract class Shape {
 abstract void calculateArea();
}

class guru99 extends Shape {
 void calculateArea() {
     System.out.println("Area of Shape");

 }
}