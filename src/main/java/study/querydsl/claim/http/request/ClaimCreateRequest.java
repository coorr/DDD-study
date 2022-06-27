package study.querydsl.claim.http.request;

import lombok.*;
import study.querydsl.claim.application.entity.ClaimType;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ClaimCreateRequest {
    @NotNull
    private UUID studentId;
    @NotNull
    private UUID targetId;

    @NotNull private ClaimType type;
}
