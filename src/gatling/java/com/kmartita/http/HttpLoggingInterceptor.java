package com.kmartita.http;


import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.Buffer;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
public class HttpLoggingInterceptor implements Interceptor {

    private static final int MAX_RESPONSE_BODY_LENGTH_FOR_DISPLAY = 500000;

    @Nonnull
    @Override
    public Response intercept(final Chain chain) throws IOException {
        final Request request = chain.request();

        for (String s : Arrays.asList("Request URL: " + request.url(), "Request Method: " + request.method())) {
            log.info(s);
        }

        final RequestBody requestBody = request.body();
        if (Objects.nonNull(requestBody)) {
            log.info("Request payload: {}", readRequestBody(requestBody));
        }

        final Response response = chain.proceed(request);
        log.info("Status code: {}", response.code());

        final ResponseBody responseBody = response.body();
        final Response.Builder responseBuilder = response.newBuilder();

        if (Objects.nonNull(responseBody)) {
            String responseContent = responseBody.string();

            if (!responseContent.contains("Could not decode response body:")) {
                if (responseContent.length() > MAX_RESPONSE_BODY_LENGTH_FOR_DISPLAY) {
                    responseContent = responseContent.substring(0, MAX_RESPONSE_BODY_LENGTH_FOR_DISPLAY);
                }

                log.info("Response body: {}", responseContent);
                responseBuilder.body(ResponseBody.create(responseBody.contentType(), responseContent.getBytes(StandardCharsets.UTF_8)));
            }
        }

        return responseBuilder.build();
    }

    private static String readRequestBody(final RequestBody requestBody) throws IOException {
        final Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        return buffer.readString(StandardCharsets.UTF_8);
    }
}

