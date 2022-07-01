package study.querydsl.provider.infrastructure.domain.vo;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(of = {"value"}, callSuper = false)
@NoArgsConstructor
@Getter
@ToString
@Embeddable
public class ProviderCode {

    private static final Integer CODE_MAX_LENGTH = 4;
    private static final String CODE_RULE = "^[\\d]+$";

    @Column(name = "code", nullable = false)
    private String value;

    public ProviderCode(@NotNull String code) {
        if (code.length() > CODE_MAX_LENGTH || !code.matches(CODE_RULE)) {
            throw new IllegalArgumentException("문제 출처 코드가 잘못되었습니다.");
        }

        this.value = String.format("%1$4s", code).replace(" ", "0");
    }

    /**
     * 출처 코드 증가
     *
     * @return
     */
    @Transient
    public ProviderCode getIncreasedCode() {
        int codeToNumber = Integer.parseInt(this.getValue());
        codeToNumber++;
        return new ProviderCode(String.valueOf(codeToNumber));
    }
}
