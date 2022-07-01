package study.querydsl.member.presentation.validator;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import study.querydsl.member.presentation.http.ProviderDepthRequest;

import java.util.Objects;

@Component
@Slf4j
public class ProviderDepthRequestValidator implements Validator {

    private static final int ROOT_LEVEL = 0;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return ProviderDepthRequest.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target,@NonNull Errors errors) {
        ProviderDepthRequest request = (ProviderDepthRequest) target;
        log.info("validate request = {}", request);
        if (request.getLevel() > ROOT_LEVEL) {
            if (Objects.isNull(request.getParentId())
                    && CollectionUtils.isEmpty(request.getParentIds())) {
                System.out.println("실행 중입니다.");
                errors.rejectValue("parentId","request.parentId", "parentId를 입력해주세요.");
            }
        }

    }
}
