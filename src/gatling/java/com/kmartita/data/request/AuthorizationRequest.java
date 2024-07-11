package com.kmartita.data.request;
import io.gatling.javaapi.core.ChainBuilder;

import static com.kmartita.config.ConfigReader.TOKEN;
import static com.kmartita.util.ApiConstants.AUTHORIZATION;
import static com.kmartita.util.ApiUrls.USER;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;

public class AuthorizationRequest {

    public static ChainBuilder getAuthorizedUser() {
        return exec(http("Get authorized user")
                .get(USER)
                .header(AUTHORIZATION, TOKEN)
                .asJson()
                .check(status().is(OK.code()))
                .check(jsonPath("$.user").exists())
                .check(jsonPath("$.user.id").notNull())
                .check(jsonPath("$.user.username").notNull())
                .check(jsonPath("$.user.email").notNull()));
    }
}
