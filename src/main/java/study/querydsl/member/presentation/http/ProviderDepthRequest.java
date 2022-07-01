package study.querydsl.member.presentation.http;

import lombok.*;
import study.querydsl.provider.application.query.field.ProviderDepthsQueryField;
import study.querydsl.provider.infrastructure.domain.vo.ProviderId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDepthRequest {
    @Builder.Default private int level = 0;

    private UUID parentId;

    @Builder.Default private List<UUID> parentIds = new ArrayList<>();

    @Builder.Default private List<ProviderStatus> statuses = List.of(ProviderStatus.USED);

    public ProviderDepthsQueryField toProviderDepthRequest() {
        return ProviderDepthsQueryField.builder()
                .level(level)
                .parentId(Objects.nonNull(parentId) ? ProviderId.of(parentId) : null)
                .parentIds(parentIds.stream().map(ProviderId::of).collect(Collectors.toList()))
                .statuses(statuses)
                .build();
    }

}
