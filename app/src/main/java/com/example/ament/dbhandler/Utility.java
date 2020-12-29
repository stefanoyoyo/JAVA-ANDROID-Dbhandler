package com.example.ament.dbhandler;

/**
 * Created by ament on 27/11/2017.
 */

public class Utility {

    public static void show (Object el) {
        System.err.println("show: " + el);
    }

    public static void showArr (Object [] o) {
        for (int i = 0; i< o.length; i++) {
            System.err.println("showArr: " + o[i]);
        }
    }
}
