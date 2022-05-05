package ru.geekbrains;

public class App {
    private String currentLetter = "A";

    public synchronized void printCurrentLetter(String printLetter, String nextPrintLetter) {
        try {
            for (int i = 0; i < 5; i++) {
                while (currentLetter != printLetter) {
                    wait();
                }
                System.out.print(currentLetter);
                setCurrentLetter(nextPrintLetter);
                notifyAll();

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setCurrentLetter(String currentLetter) {
        this.currentLetter = currentLetter;
    }

    public static void main(String[] args) {

        App a = new App();
        new Thread(new Runnable() {
            @Override
            public void run() {
                a.printCurrentLetter("A", "B");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                a.printCurrentLetter("B", "C");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                a.printCurrentLetter("C", "A");
            }
        }).start();


    }
}
