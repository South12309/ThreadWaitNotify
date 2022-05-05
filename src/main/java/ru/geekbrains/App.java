package ru.geekbrains;

public class App {
    private String currentLetter;

    public synchronized void printCurrentLetter(String printLetter, String nextPrintLetter) {
        for (int i = 0; i < 5; i++) {
            while (currentLetter != printLetter) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(currentLetter);
            setCurrentLetter(nextPrintLetter);
            notifyAll();
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



    }
}
