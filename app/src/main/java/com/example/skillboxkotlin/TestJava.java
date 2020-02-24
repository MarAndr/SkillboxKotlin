package com.example.skillboxkotlin;

public class TestJava {

    public static void main(String[] args) {
        TestJava testJava = new TestJava();
        System.out.println(testJava.nod(60,20));


    }

    public int nod(int a, int b){
        int c = 0;
        if (a != b){
            while (a != b){
                if (a > b){
                    c = a - b;
                    a = c;
                } else {
                    c = b - a;
                    b = c;
                }
            }
            return a;
        } else {
            return a;
        }

    }
}


