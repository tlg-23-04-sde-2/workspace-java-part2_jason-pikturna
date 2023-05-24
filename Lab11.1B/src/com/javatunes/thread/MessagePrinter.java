/*
 * This code is sample code, provided AS-IS, and we make NO
 * warranties as to its correctness or suitability for any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.thread;


public class MessagePrinter implements Runnable {
    private String message;
    private int count = 10;
    private long interval = 1000;
    private String name;


    public MessagePrinter(String message) {
        this.message = message;
    }


    public MessagePrinter(String message, int count, long interval) {
        this.message = message;
        setCount(count);
        setInterval(interval);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public String getMessage() {
        return message;
    }

    public int getCount() {
        return count;
    }

    public long getInterval() {
        return interval;
    }

    /**
     * TODO: implement run() as follows:
     * It should loop 10 times, printing the 'message' field to stdout,
     * then pausing for some interval (in millis) that you choose.
     * <p>
     * Use a try/catch to call Thread.sleep(interval) for the pause.
     * The sleep() method throws InterruptedException, which you need to catch.
     * You can either leave the catch block empty, or print the exception to stdout.
     */
    public void run() {
        for (int i = 0; i < getCount(); i++) {
            System.out.println(Thread.currentThread().getName() + ": " + message);
            try {
                Thread.sleep(getInterval());
            } catch (InterruptedException ignored) {

            }
        }
    }
}