package com.threeSidedSquareStudios.engine.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Time {
    public static final long SECOND = (long) 1000000000.0;

    public static double getTime() {
        return (double) System.nanoTime() / (double) SECOND;
    }

    public static String getFormatTime() {
        return getFormattedTime("HH:mm:ss");
    }

    public static String getFormattedTime(String format) {
        return new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
    }
}
