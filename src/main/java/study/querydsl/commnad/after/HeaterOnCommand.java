package study.querydsl.commnad.after;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import study.querydsl.commnad.before.Heater;

@NoArgsConstructor
@AllArgsConstructor
public class HeaterOnCommand implements Command {
    private Heater heater;

    @Override
    public void execute() {
        heater.powerOn();
    }
}
