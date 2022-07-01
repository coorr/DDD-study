package study.querydsl.commnad.after;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class OkGoogles {
    private Command command;

    void talk() {
        command.execute();
    }
}
