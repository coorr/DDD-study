package study.querydsl.claim.application.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(of = "value", callSuper = false)
@Embeddable
public class ClaimId implements Serializable {
    private UUID value;

    public static ClaimId of(UUID id) {
        return new ClaimId(id);
    }
}
