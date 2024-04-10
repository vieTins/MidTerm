package Midterm;

import java.util.List;
import java.util.concurrent.BlockingQueue;

class PrimeChecker implements Runnable {
    private BlockingQueue<Student> inputQueue;
    private List<Student> resultStudents;

    public PrimeChecker(BlockingQueue<Student> inputQueue, List<Student> resultStudents) {
        this.inputQueue = inputQueue;
        this.resultStudents = resultStudents;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Student student = inputQueue.take();
                resultStudents.add(student); // Add student to the result list
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

