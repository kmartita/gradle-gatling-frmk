package com.kmartita.scenario;

import io.gatling.javaapi.core.ScenarioBuilder;

import static com.kmartita.data.request.AuthorizationRequest.*;
import static io.gatling.javaapi.core.CoreDsl.scenario;

public class AuthorizedUserScenario {

    public static ScenarioBuilder getAuthorizedUserScenario() {
        return scenario("Fetch Authorized User scenario")
                .exec(getAuthorizedUser());
    }
}
