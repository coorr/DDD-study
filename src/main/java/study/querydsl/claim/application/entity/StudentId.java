package study.querydsl.claim.application.entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@ToString
@Getter
@EqualsAndHashCode(of = "value", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class StudentId implements Serializable {
    private UUID value;

    public static StudentId of(UUID id) {
        return new StudentId(id);
    }
}
