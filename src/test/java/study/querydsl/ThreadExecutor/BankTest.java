package study.querydsl.ThreadExecutor;

import org.junit.jupiter.api.Test;


class BankTest {

    @Test
    void testt() {
        Bank th1 = new Bank("ATM");
        Bank th2 = new Bank("은행");

        th1.start();
        th2.start();

    }
}