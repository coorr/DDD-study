package study.querydsl.commnad.after;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import study.querydsl.commnad.before.Lamp;

@NoArgsConstructor
@AllArgsConstructor
public class LampOnCommand implements Command {
    private Lamp lamp;

    @Override
    public void execute() {
        lamp.turnOn();

    }
}
