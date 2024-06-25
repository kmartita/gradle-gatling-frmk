package com.kmartita.request;

import com.kmartita.util.Verification;
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.CoreDsl;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.*;

import static com.kmartita.config.ConfigReader.TOKEN;
import static com.kmartita.util.Constant.AUTHORIZATION;
import static com.kmartita.util.RequestConstant.SPACE;
import static com.kmartita.util.RequestConstant.SPACES;
import static com.kmartita.request.TeamRequest.getTeamId;
import static com.kmartita.util.SessionMapping.SPACE_ID;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.jsonPath;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;

public class SpaceRequest {

    public static ChainBuilder getEmptySpaces() {
        return exec(getTeamId())
                .exec(http("Get empty spaces")
                        .get(SPACES)
                        .header(AUTHORIZATION, TOKEN)
                        .queryParam("archived", "false")
                        .asJson()
                        .check(status().is(OK.code()))
                        .check(jsonPath("$.spaces").exists())
                        .check(jsonPath("$.spaces").ofList().transform(List::isEmpty)
                                .is(true)));
    }

    public static ChainBuilder getAllSpaces() {
        return exec(getTeamId())
                .exec(http("Get all spaces")
                        .get(SPACES)
                        .header(AUTHORIZATION, TOKEN)
                        .queryParam("archived", "false")
                        .asJson()
                        .check(status().is(OK.code()))
                        .check(jsonPath("$.spaces").exists())
                        .check(jsonPath("$.spaces").ofList().transform(spaces -> !spaces.isEmpty())
                                .is(true)));
    }

    public static ChainBuilder createSpace() {
        String randomName = RandomStringUtils.randomAlphanumeric(8);
        return exec(getTeamId())
                .exec(session -> session.set("randomName", randomName))
                .exec(http("Create new space")
                        .post(SPACES)
                        .body(CoreDsl.ElFileBody("json/space.json"))
                        .asJson()
                        .check(status().is(OK.code()))
                        .check(Verification.verifyResponseId().saveAs(SPACE_ID)));
    }

    public static ChainBuilder updateSpace() {
        String randomName = RandomStringUtils.randomAlphanumeric(5);
        return exec(session -> session.set("randomName", randomName))
                .exec(http("Update space by id")
                .put(SPACE)
                .body(CoreDsl.ElFileBody("json/space.json"))
                .asJson()
                .check(status().is(OK.code()))
                .check(Verification.verifyResponseId()));
    }

    public static ChainBuilder getSpaceById() {
        return exec(http("Get Space by id")
                .get(SPACE)
                .asJson()
                .check(status().is(OK.code()))
                .check(Verification.verifyResponseId()));
    }

    public static ChainBuilder deleteSpaceById() {
        return exec(http("Delete Space by id")
                .delete(SPACE)
                .asJson()
                .check(status().is(OK.code())));
               /* .check(jsonPath("$..*").count().is(0)))*/
    }
}
