package study.querydsl.provider.infrastructure.domain.vo;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@ToString
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProviderId implements Serializable {
    @Column(name = "id")
    private UUID value;

    public static ProviderId of(UUID id) {
        return new ProviderId(id);
    }
}
