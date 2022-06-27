package study.querydsl.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HttpApiResponse<T> {
    private T data;
    private Map<String, Object> meta;

    public HttpApiResponse(T data) {
        this.data = data;
    }

    public HttpApiResponse(T data, Map<String, Object> meta) {
        this.data = data;
        this.meta = meta;
    }

    public static <T> HttpApiResponse<T> of(T data, Map<String, Object> meta) {
        return new HttpApiResponse<>(data, meta);
    }

    public static <T> HttpApiResponse<T> of(T data) {
        return new HttpApiResponse<>(data, null);
    }
}
