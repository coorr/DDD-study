package study.querydsl.tdd;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Money implements Expression {
    protected int amount;
    protected String currency;

    static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    Expression plus(Money addend) {
        return new Money(amount + addend.amount, currency);
    }
}
