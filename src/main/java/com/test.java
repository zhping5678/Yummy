package com;

public class test {
    public static void main(String[] args){
        String test = "xxxx";
        String[] tests= test.split(",");
        for(String s:tests){
            System.out.println(s);
        }
        System.out.println("size: "+tests.length);
    }
}
