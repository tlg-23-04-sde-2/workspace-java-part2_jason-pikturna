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
        MessagePrinter printer1 = new MessagePrinter("Roses are Red", 10, 1000);
        Thread thd1 = new Thread(printer1, "Message-Printer-1");
        thd1.start();
        MessagePrinter printer2 = new MessagePrinter("Violets are blue", 10, 500);
        Thread thd2 = new Thread(printer2, "Message-Printer-2");
        thd2.start();
        // TODO: create two instances of your thread subclass and start them up
    }
}