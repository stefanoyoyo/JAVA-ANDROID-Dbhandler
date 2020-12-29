package com.example.ament.dbhandler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe funzionante in java ma non in android
 */

public class EditTextUpdateOnTime {


    public static void timer() {

        TimerTask tasknew = new MyTemporizedTask();
        Timer timer = new Timer();

    /* scheduling the task, the first argument is the task you will be
     performing, the second is the delay, and the last is the period. */
        timer.schedule(tasknew, 1000, 1000);
    }
}
