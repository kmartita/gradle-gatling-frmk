package com.kmartita.data.request;

import com.kmartita.config.ConfigReader;
import io.gatling.javaapi.core.ChainBuilder;

import java.util.Objects;

import static com.kmartita.config.ConfigReader.TOKEN;
import static com.kmartita.util.ApiConstants.AUTHORIZATION;
import static com.kmartita.util.ApiUrls.TEAMS;
import static com.kmartita.util.SessionMapping.TEAM_ID;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;

public class TeamRequest {

    public static ChainBuilder getTeamId() {
        String username = Objects.requireNonNull(ConfigReader.getConfig().getWorkspace().getUsername(),
                "'workspace.username' property is not provided");

        return exec(http("Get team id")
                .get(TEAMS)
                .header(AUTHORIZATION, TOKEN)
                .asJson()
                .check(status().is(OK.code()))
                .check(jsonPath(String.format("$.teams[?(@.members[*].user.username == '%s')].id", username))
                        .find().optional().saveAs(TEAM_ID)));
    }
}
