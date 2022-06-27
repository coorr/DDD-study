package study.querydsl.claim.application.entity;

import lombok.*;
import org.hibernate.annotations.Where;
import study.querydsl.claim.application.domain.event.BaseEntityAggregateRoot;
import study.querydsl.claim.application.domain.event.RegisteredClaim;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Where(clause = "deleted_at is null")
public class Claim extends BaseEntityAggregateRoot<Claim> {
    @EmbeddedId
    @AttributeOverride(name = "value", column = @Column(name = "id"))
    private ClaimId id;

    @NotNull
    @Embedded
    @AttributeOverride(name = "value" , column = @Column(name = "student_id"))
    private StudentId studentId;

    @NotNull
    @Embedded
    @AttributeOverride(name = "value" , column = @Column(name = "target_id"))
    private TargetId targetId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ClaimType type;

    public static Claim create(StudentId studentId, TargetId targetId, ClaimType type) {
        var claim =
                Claim.builder()
                        .id(ClaimId.of(UUID.randomUUID()))
                        .studentId(studentId)
                        .targetId(targetId)
                        .type(type)
                        .build();
        claim.registerEvent(new RegisteredClaim());
        return claim;
    }

}
