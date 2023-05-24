/*
 * This code is sample code, provided AS-IS, and we make NO
 * warranties as to its correctness or suitability for any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.thread;

public class MessagePrinterClient {



    public static void main(String[] args) {
        MessagePrinter printer1 = new MessagePrinter("roses are red", 5, 5000);
        printer1.start();
        MessagePrinter printer2 = new MessagePrinter("violets are blue", 30, 500);
        printer2.start();
        // TODO: create two instances of your thread subclass and start them up
    }
}