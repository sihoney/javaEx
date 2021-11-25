package com.javaex.helloworld;

//A static block helps to initialize the static data members.
//just like constructors help to initialize instance members.

public class StaticBlock {
 
 static int a;
 static int b;
 static {
     a = 10;
     b = 20;
 }
 
 public static void main(String args[]){
     System.out.println("Value of a =  "+a);
     System.out.println("Value of b = "+b);
 }
}