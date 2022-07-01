package study.querydsl.provider.presentation.http;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import study.querydsl.config.TestBaseConfig;
import study.querydsl.member.presentation.http.ProviderStatus;
import study.querydsl.provider.infrastructure.domain.entity.Provider;
import study.querydsl.provider.infrastructure.domain.repository.ProviderRepository;
import study.querydsl.provider.infrastructure.domain.vo.ProviderCode;
import study.querydsl.provider.infrastructure.domain.vo.ProviderId;

import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("출처 관리")
@Transactional
class ProviderControllerTest extends TestBaseConfig {
    private final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

    @Autowired
    ProviderRepository repository;

    private Provider provider;

    @Nested
    class ListApiTest {

        @BeforeEach
        void setUp() {
            provider =
                    Provider.builder()
                            .id(new ProviderId(UUID.randomUUID()))
                            .level(0)
                            .name("test")
                            .fullCode("1111")
                            .fullName("1111")
                            .code(new ProviderCode("1111"))
                            .status(ProviderStatus.USED)
                            .build();

            provider.addChild(
                    new ProviderId(UUID.randomUUID()), "2222", ProviderStatus.USED, new ProviderCode("2222"));

            repository.saveAndFlush(provider);

            params.add("statuses", ProviderStatus.USED.toString());
    }

        private ResultActions getProviderMvcTemplate(MultiValueMap<String, String> params) throws Exception {
            return mockMvc.perform(
                    MockMvcRequestBuilders.get("/providers")
                            .params(params)
                            .accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
            );
        }
    }


    @Nested
    class DepthListApiTest {
        private final ProviderId parentId = new ProviderId(UUID.randomUUID());

        @BeforeEach
        void setUp() {
            provider =
                    Provider.builder()
                            .id(parentId)
                            .level(0)
                            .name("테스트 출처")
                            .code(new ProviderCode("1111"))
                            .fullCode("1111")
                            .fullName("1111")
                            .status(ProviderStatus.USED)
                            .build();

            provider.addChild(
                    new ProviderId(UUID.randomUUID()), "2222", ProviderStatus.USED, new ProviderCode("2222"));

            provider.addChild(
                    new ProviderId(UUID.randomUUID()),
                    "3333",
                    ProviderStatus.UNUSED,
                    new ProviderCode("3333"));

            repository.saveAndFlush(provider);
            params.add("level", "0");
        }

        @Test
        @DisplayName("Depth 리스트 BadRequest : level is 1 and parentId is null")
        void getDepthListBadRequestByLevelIsPositiveAndParentIdIsNull() throws Exception {
            params.set("level", "1");

            this.getDepthMvcTemplate(params)
                    .andDo(print())
                    .andExpect(status().isBadRequest());
        }

        private ResultActions getDepthMvcTemplate(MultiValueMap<String, String> params) throws Exception {
            return mockMvc.perform(
                    MockMvcRequestBuilders.get("/provider-depths")
                            .params(params)
                            .accept(MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON_VALUE)
            );
        }
    }


}