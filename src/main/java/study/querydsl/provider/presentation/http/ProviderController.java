package study.querydsl.provider.presentation.http;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import study.querydsl.kernal.exception.NotFoundException;
import study.querydsl.member.presentation.http.ProviderDepthRequest;
import study.querydsl.member.presentation.validator.ProviderDepthRequestValidator;
import study.querydsl.provider.application.query.ProviderQuery;
import study.querydsl.provider.application.query.result.ProviderDepthsQueryResult;
import study.querydsl.provider.infrastructure.domain.vo.ProviderId;
import study.querydsl.provider.presentation.http.request.ProviderUpdateRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProviderController {
    private final ProviderDepthRequestValidator depthRequestValidator;
    private final ProviderQuery query;




    @GetMapping("/provider-depths")
    public ResponseEntity<List<ProviderDepthsQueryResult>> getDepthList(@Valid ProviderDepthRequest dto, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }

        depthRequestValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return ResponseEntity.ok().body(query.getDepthsBy(dto.toProviderDepthRequest()));
    }

    @GetMapping("/provider/{id}")
    public ResponseEntity<Object> getProvider(@PathVariable(value = "id") UUID id) {
        var result = query.getProvider(ProviderId.of(UUID.randomUUID()));
        if (Objects.isNull(result)) {
            throw new NotFoundException(String.format("%s 출처가 존재하지 않습니다.", id));
        }

        return ResponseEntity.ok().body(result);
    }


    @PutMapping("/providers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody ProviderUpdateRequest dto, @PathVariable UUID id) {
        if (StringUtils.hasText(dto.getName())) {
//            dto.setName(StringUtils.);
        }
    }
}
