
package main;

import order.Customer;
import order.OrderProcessor;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static int noOrders = 100000;

    public static void main(String[] args) {

        String[] names = new String[]{"Jan", "Piet", "Joris", "Corneel"};
        List<Thread> threads = new ArrayList<>();
        OrderProcessor orderProcessor = new OrderProcessor();

        for (String name : names) {
            Thread t = new Thread(new Customer(name));
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Processed orders: " + OrderProcessor.getNumberOfOrders());
    }
}