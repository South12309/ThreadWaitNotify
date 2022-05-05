package ru.geekbrains;

public class LettersInTurn {
    private String currentLetter = "A";

    public synchronized void printCurrentLetter(String printLetter, String nextPrintLetter) {
        try {
            for (int i = 0; i < 5; i++) {
                while (!currentLetter.equals(printLetter)) {
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

        LettersInTurn a = new LettersInTurn();
        new Thread(() -> a.printCurrentLetter("A", "B")).start();
        new Thread(() -> a.printCurrentLetter("B", "C")).start();
        new Thread(() -> a.printCurrentLetter("C", "A")).start();


    }
}
