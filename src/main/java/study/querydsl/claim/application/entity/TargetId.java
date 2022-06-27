package study.querydsl.claim.application.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode(of = "value", callSuper = false)
public class TargetId implements Serializable {
    private UUID value;

    public static TargetId of(UUID id) {
        return new TargetId(id);
    }
}
