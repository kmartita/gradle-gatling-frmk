package com.kmartita.http;

import com.kmartita.config.ConfigReader;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import java.util.Map;

import static com.kmartita.util.ApiConstants.AUTHORIZATION;
import static io.gatling.javaapi.http.HttpDsl.http;

public class HttpProtocolConfig {

    public static HttpProtocolBuilder getHttpProtocol() {
        return http
                .baseUrl(ConfigReader.BASE_URL)
                .headers(prepareHeaders());
    }

    private static Map<CharSequence, String> prepareHeaders() {
        return Map.ofEntries(
                Map.entry("Accept", "*/*"),
                Map.entry("Content-Type", "application/json"),
                Map.entry(AUTHORIZATION, ConfigReader.TOKEN),
                Map.entry("host", "api.clickup.com"));
    }
}
