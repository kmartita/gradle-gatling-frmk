package com.kmartita.http;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpRequestExecutor {

    @SneakyThrows
    public static <S> Response<S> execute(Call<S> caller, int code) {
        Response<S> response = caller.execute();
        assertThat(response.code())
                .describedAs("Status code is not as expected")
                .isEqualTo(code);
        return response;
    }
}
