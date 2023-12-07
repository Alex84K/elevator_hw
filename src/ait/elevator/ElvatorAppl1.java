package ait.elevator;

import ait.elevator.model.Elevator;
import ait.elevator.task.Truck;

public class ElvatorAppl1 {
    private static final int N_TRUK = 1000;
    private static final int N_RACES = 10;
    private static final int CAPACITY = 20;

    public static void main(String[] args) throws InterruptedException {
        Elevator lenin = new Elevator("V.I.Lenin");
        Elevator stalin = new Elevator("I.V.Stalin");
        Truck[] trucks = new Truck[N_TRUK];
        for (int i = 0; i < trucks.length; i++) {
            trucks[i] = new Truck(N_RACES, CAPACITY, lenin);
        }
        Thread[] threads = new Thread[N_TRUK];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(trucks[i]);
            threads[i].start();
        }
        for (Thread thread: threads) {
            thread.join();
        }

        //теперь все на др элеватор
        for (int i = 0; i < trucks.length; i++) {
            trucks[i] = new Truck(N_RACES, CAPACITY, stalin);
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(trucks[i]);
            threads[i].start();
        }
        for (Thread thread: threads) {
            thread.join();
        }

        int all = lenin.getCurrentVolume() + stalin.getCurrentVolume();
        System.out.println("Elevator " + lenin.getName() + " has " + lenin.getCurrentVolume());
        System.out.println("Elevator " + stalin.getName() + " has " + stalin.getCurrentVolume());
        System.out.println("All = " + all);

    }
}
