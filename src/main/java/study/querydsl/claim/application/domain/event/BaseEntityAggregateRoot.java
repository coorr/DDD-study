package study.querydsl.claim.application.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@Where(clause = "deleted_at is null")
@EntityListeners(AuditingEntityListener.class)
public class BaseEntityAggregateRoot<T extends BaseEntityAggregateRoot<T>> extends AbstractAggregateRoot<T> {
    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP", updatable = false)
    protected LocalDateTime createdAt;

    @LastModifiedDate
    @Column(columnDefinition = "TIMESTAMP")
    protected LocalDateTime updatedAt;

    @Column(name = "deleted_at", columnDefinition = "TIMESTAMP")
    protected LocalDateTime deletedAt;

}
