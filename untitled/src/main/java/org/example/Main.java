package org.example;

import org.example.api.MyAPI;
import org.example.model.MessageBean;
import org.example.model.StudentBean;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        try {
            //System.out.println( MyAPI.sendGet("http://localhost:8080/student"));

//            StudentBean studentBean = MyAPI.getStudent();
//            System.out.println(studentBean);

            MyAPI.sendMessage();
            MyAPI.sendMessage();
            MyAPI.sendMessage();

            MessageBean [] messages = MyAPI.getMessage();
            System.out.println(Arrays.toString(messages));




        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}