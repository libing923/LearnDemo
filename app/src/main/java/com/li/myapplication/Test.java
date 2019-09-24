package com.li.myapplication;

import java.util.Scanner;

public class Test {

    private static void hanoi(int n, char a, char b, char c) {
        if (n < 1) return;
        if (n == 1) {
            System.out.println("Move 1 from " + a + " to " + b);
            return;
        }
        hanoi(n - 1, a, c, b);
        System.out.println("Move " + n + " from " + a + " to " + c);
        hanoi(n - 1, c, b, a);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        hanoi(n, 'A', 'B', 'C');
    }
}
