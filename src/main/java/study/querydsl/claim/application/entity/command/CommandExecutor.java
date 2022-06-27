package study.querydsl.claim.application.entity.command;

import study.querydsl.claim.application.model.CommandModel;

public class CommandExecutor<T extends CommandModel> {
    private final Command<T> command;

    private final T t;

    public CommandExecutor(Command<T> command, T t) {
        this.command = command;
        this.t = t;
    }

    public void invoke() {
        command.execute(t);
    }

}
