package study.querydsl.ThreadExecutor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private int balance = 1000;

    public void withdraw(int money) {
        System.out.println("현재 돈 빼기: " + money);
        if (balance >= money) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
            balance -= money;
        }
    }


    public void withup(int money) {
        System.out.println("현재 돈 더하기: " + money);
        if (balance <= money) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
            balance += money;
        }
    }
}
