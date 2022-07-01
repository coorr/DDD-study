package study.querydsl.ThreadExecutor;

import lombok.AllArgsConstructor;

import java.util.concurrent.Semaphore;

@AllArgsConstructor
public class SyncMulti extends Thread {
    Semaphore sem;
    String msg;

    public void run() {
        try {
            sem.acquire();
            System.out.println(msg);
            Thread.sleep(1000);
            sem.release();
        } catch (Exception e) {

        }
    }
}
