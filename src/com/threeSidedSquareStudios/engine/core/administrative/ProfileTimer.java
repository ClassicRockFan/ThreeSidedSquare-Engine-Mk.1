package com.threeSidedSquareStudios.engine.core.administrative;


import com.threeSidedSquareStudios.engine.core.Time;


public class ProfileTimer {

    private long numInvocations;
    private double totalTime;
    private double startTime;

    public ProfileTimer() {
        numInvocations = 0;
        totalTime = 0;
        startTime = 0;
    }

    public void startInvocation() {
        startTime = Time.getTime();
    }

    public void stopInvocation() {
        if (startTime == 0) {
            System.err.println("Error: stopInvocation() called without mathing startInvocation()");
            System.exit(1);
        } else {
            numInvocations++;
            totalTime += (Time.getTime() - startTime);
            startTime = 0;
        }
    }

    public double displayAndReset(String message) {
        return displayAndReset(message, 0);
    }

    public double reset(double dividend) {
        double time;

        if (dividend != 0)
            time = 1000.0 * totalTime / dividend;
        else
            time = 1000.0 * totalTime / (double) (numInvocations);

        totalTime = 0.0;
        numInvocations = 0;

        return time;
    }

    public double displayAndReset(String message, double dividend) {
        double time = reset(dividend);
        System.out.println(message + time + " ms");

        return time;
    }
}
