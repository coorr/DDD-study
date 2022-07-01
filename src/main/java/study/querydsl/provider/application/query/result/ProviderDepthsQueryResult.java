package study.querydsl.provider.application.query.result;

import lombok.*;
import study.querydsl.member.presentation.http.ProviderStatus;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProviderDepthsQueryResult {
    private UUID id;
    private String code;
    private UUID parentId;
    private Integer level;
    private String name;
    private ProviderStatus status;
    private int childCount;
}
