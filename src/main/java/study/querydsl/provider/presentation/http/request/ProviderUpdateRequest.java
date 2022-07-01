package study.querydsl.provider.presentation.http.request;

import lombok.*;
import study.querydsl.member.presentation.http.ProviderStatus;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProviderUpdateRequest {
    @NotNull
    private String name;

    private ProviderStatus status;

    public ProviderUpdateRequest toProviderUpdateCommandRequest() {
        return ProviderUpdateRequest.builder().name(name).status(status).build();
    }

}
