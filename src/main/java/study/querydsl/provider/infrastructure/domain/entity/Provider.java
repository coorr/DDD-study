package study.querydsl.provider.infrastructure.domain.entity;

import lombok.*;
import org.hibernate.annotations.Where;
import study.querydsl.claim.application.domain.event.BaseEntityAggregateRoot;
import study.querydsl.member.presentation.http.ProviderStatus;
import study.querydsl.provider.infrastructure.domain.vo.ProviderCode;
import study.querydsl.provider.infrastructure.domain.vo.ProviderId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;

@Builder
@ToString
@EqualsAndHashCode(of = "id", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Where(clause = "deleted_at is null")
public class Provider extends BaseEntityAggregateRoot<Provider> {

    @EmbeddedId
    private ProviderId id;

    @ManyToOne(
            fetch = LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "parent_id")
    private Provider parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true, cascade = CascadeType.ALL)
    private final Set<Provider> children = new HashSet<>();

    @NotNull
    private String name;

    @Column(nullable = false, unique = true)
    private String fullName;

    @Embedded
    @NotNull
    private ProviderCode code;

    @Column(nullable = false, unique = true)
    private String fullCode;

    @NotNull
    private Integer level;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProviderStatus status;


    public void addChild(ProviderId id, String name, ProviderStatus status, ProviderCode newCode) {
        this.children.add(
                Provider.builder()
                        .id(id)
                        .parent(this)
                        .name(name)
                        .fullName(this.generateFullName(name, this))
                        .code(newCode)
                        .fullCode(this.generateFullCode(newCode, this))
                        .level(this.level + 1)
                        .status(status)
                        .build());
    }
    private String generateFullName(String name, Provider parent) {
        if (Objects.isNull(parent)) {
            return name;
        }

        return String.format("%s %s", parent.getFullName(), name);
    }

    private String generateFullCode(ProviderCode code, Provider parent) {
        if (Objects.isNull(parent)) {
            return code.getValue();
        }

        return String.format("%s/%s", parent.getFullCode(), code.getValue());
    }
}
