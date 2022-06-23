package com.company;

import com.company.factory.ClassFactory;

// architechture of dependencies , relationships between classes
public class Main {
    public static void main(String[] args) {
        //@Dao
        System.out.println("main class is running");
        ClassFactory classFactory = new ClassFactory();
    }

}
