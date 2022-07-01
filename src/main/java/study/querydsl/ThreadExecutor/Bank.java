package study.querydsl.ThreadExecutor;

public class Bank extends Thread {
    static Account obj = new Account();

    public Bank(String name) {
        super(name);
    }

    public void run() {
//        while (true) {
            synchronized (obj) {
                int money = (int) ((Math.random() * 1 + 3) * 100);
                if (obj.getBalance() >= money) {
                    System.out.println(getName() + "의 balance : " + obj.getBalance());
                    System.out.println(getName() + "의 찾는 금액 : " + money);
                    obj.withdraw(money);
                    System.out.println(getName() + "의 balence : " + obj.getBalance());
                } else {
                    System.out.println(getName() + "의 balance : " + obj.getBalance());
                    System.out.println(getName() + "의 찾는 금액 : " + money);
                    obj.withup(money);
                    System.out.println(getName() + "의 balence : " + obj.getBalance());
                }
//            }
        }
    }
}
