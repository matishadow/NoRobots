package com.norobots.io;

import java.util.Scanner;

public class UserInputGetter {
    public String getLogin() {
        System.out.println("Enter your username: ");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }

    public String getPassword() {
        System.out.println("Enter your password: ");
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine();
    }
}
