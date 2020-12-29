/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ament.dbhandler;

import java.util.TimerTask;

/**
 *
 * @author ament
 */
 class MyTemporizedTask extends TimerTask {

        DBactivity dBactivity = new DBactivity();


        @Override
        public void run() {
            System.out.println("Hello world from Timer task!");
            dBactivity.saveInSupportDatabase();
        }
    }