package study.querydsl.claim.application.entity.command;

import lombok.*;
import study.querydsl.claim.application.entity.ClaimType;
import study.querydsl.claim.application.model.CommandModel;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateClaimModel implements CommandModel {
    @NotNull private UUID studentId;
    @NotNull private UUID targetId;
    @NotNull private ClaimType type;
}
