package study.querydsl.provider.application.query.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.querydsl.member.presentation.http.ProviderStatus;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProviderQueryResult {
    private UUID id;
    private UUID parentId;
    private String name;
    private String fullName;
    private String code;
    private String fullCode;
    private Integer level;
    private ProviderStatus status;
}
