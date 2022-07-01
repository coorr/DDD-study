package study.querydsl.provider.application.query;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import study.querydsl.member.presentation.http.ProviderStatus;
import study.querydsl.provider.application.query.field.ProviderDepthsQueryField;
import study.querydsl.provider.application.query.result.ProviderDepthsQueryResult;
import study.querydsl.provider.infrastructure.domain.repository.ProviderRepository;
import study.querydsl.provider.infrastructure.domain.vo.ProviderCode;
import study.querydsl.provider.infrastructure.domain.vo.ProviderId;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@DisplayName("출처 쿼리(ProviderQuery) 테스트")
@ExtendWith(MockitoExtension.class)
class ProviderQueryTest {

    @InjectMocks
    ProviderQuery query;

    @Mock
    ProviderSupport support;

    @Mock
    ProviderRepository repository;


    @Nested
    class DepthList {

        private final ProviderId parentId = new ProviderId(UUID.randomUUID());

        @DisplayName("[mock] 출처 Depth 리스트 by level")
        @Test
        void getDepthsByWithMock() {
            ProviderDepthsQueryField request = spy(ProviderDepthsQueryField.class);

            given(support.findDepthsBy(request)).willReturn(Arrays.asList());

            query.getDepthsBy(request);


            then(support).should(atLeastOnce()).findDepthsBy(request);
        }

        @DisplayName("[mock] 출처 Depth(Level) 리스트 by level And ParentId")
        @Test
        void getDepthsByLevelAndParentIdWithMock() {
            ProviderDepthsQueryField request = spy(ProviderDepthsQueryField.class);
            var parentId = mock(ProviderId.class);


            given(support.findDepthsBy(request)).willReturn(Arrays.asList());

            query.getDepthsBy(request);

            then(support).should(atLeast(1)).findDepthsBy(request);

        }

        @DisplayName("출처 Depth(Level) 리스트")
        @Test
        void getDepthByLevelAndParentId() {
            ProviderDepthsQueryField depthRequest = ProviderDepthsQueryField.builder().level(1).build();

            List<ProviderDepthsQueryResult> depthsBy = query.getDepthsBy(depthRequest);

            assertThat(depthsBy).isNotNull();
        }

        @Test
        @DisplayName("출처에 계층이 있는 리스트를 반환할 수 있어야 한다")
        void should_return_non_empty_collection_when_call_getDepthsBy() {
            var request =
                    ProviderDepthsQueryField.builder()
                            .level(1)
                            .parentId(ProviderId.of(UUID.randomUUID()))
                            .statuses(List.of(ProviderStatus.USED))
                            .build();

            System.out.println("request = " + request);

            final var resultProviderId =ProviderId.of(UUID.randomUUID());

            System.out.println("resultProviderId = " + resultProviderId);

            when(support.findDepthsBy(request))
                    .thenReturn(
                            List.of(
                                    ProviderDepthsQueryResult.builder()
                                            .id(resultProviderId.getValue())
                                            .code(new ProviderCode("0001").getValue())
                                            .name("TEST_PROVIDER_NAME")
                                            .level(1)
                                            .parentId(request.getParentId().getValue())
                                            .status(ProviderStatus.USED)
                                            .childCount(1)
                                            .build()

                            ));

            final var result = query.getDepthsBy(request);
            for (ProviderDepthsQueryResult providerDepthsQueryResult : result) {
                System.out.println("providerDepthsQueryResult = " + providerDepthsQueryResult);
            }

            assertThat(result)
                    .isNotEmpty()
                    .element(0)
                    .extracting("id", "code", "name", "level", "status", "childCount")
                    .containsExactly(
                            resultProviderId.getValue(), "0001", "TEST_PROVIDER_NAME", 1, ProviderStatus.USED, 1
                    );



        }
    }
}