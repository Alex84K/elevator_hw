package ait.elevator;

import ait.elevator.model.Elevator;
import ait.elevator.task.Truck;

public class Elevator2Appl {
    private static final int N_TRUK = 1000;
    private static final int N_RACES = 10;
    private static final int CAPACITY = 20;

    public static void main(String[] args) throws InterruptedException {
        Elevator lenin = new Elevator("V.I.Lenin");
        Elevator stalin = new Elevator("I.V.Stalin");
        Truck[] trucks1 = new Truck[N_TRUK];
        Truck[] trucks2 = new Truck[N_TRUK];
        for (int i = 0; i < trucks1.length; i++) {
            trucks1[i] = new Truck(N_RACES, CAPACITY, lenin); // эти начнут с Ленина
            trucks2[i] = new Truck(N_RACES, CAPACITY, stalin);// эти начнут со Сталина
        }
        Thread[] threads1 = new Thread[N_TRUK];
        Thread[] threads2 = new Thread[N_TRUK];
        for (int i = 0; i < threads1.length; i++) {
            threads1[i] = new Thread(trucks1[i]);
            threads2[i] = new Thread(trucks2[i]);
            threads1[i].start();
            threads2[i].start();
        }
        for (Thread thread1: threads1) {
            thread1.join();
            for (Thread thread2: threads1){
                thread2.join();
            }
        }

        int all = lenin.getCurrentVolume() + stalin.getCurrentVolume();
        System.out.println("Elevator " + lenin.getName() + " has " + lenin.getCurrentVolume());
        System.out.println("Elevator " + stalin.getName() + " has " + stalin.getCurrentVolume());
        System.out.println("All = " + all);
    }
}
