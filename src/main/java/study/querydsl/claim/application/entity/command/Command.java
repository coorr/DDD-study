package study.querydsl.claim.application.entity.command;

import study.querydsl.claim.application.model.CommandModel;

public interface Command<T extends CommandModel> {
    void execute(T t);
}
