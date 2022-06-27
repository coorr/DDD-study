package study.querydsl.claim.application.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredClaim {
    private String type;
    private UUID targetId;

    public static RegisteredClaim create(String type, UUID targetId) {
        return RegisteredClaim.builder().type(type).targetId(targetId).build();
    }
}
