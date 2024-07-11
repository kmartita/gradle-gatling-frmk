package com.kmartita.simulation;

import com.kmartita.http.HttpProtocolConfig;
import io.gatling.javaapi.core.Simulation;

import java.time.Duration;

import static com.kmartita.scenario.AuthorizedUserScenario.*;
import static io.gatling.javaapi.core.CoreDsl.*;

public class AuthorizedUserSimulation extends Simulation {

    {
        setUp(getAuthorizedUserScenario()
                .injectOpen(constantUsersPerSec(20).during(Duration.ofSeconds(20)),
                        rampUsersPerSec(20).to(100).during(Duration.ofSeconds(30)))
                .protocols(HttpProtocolConfig.getHttpProtocol()));
    }
}
