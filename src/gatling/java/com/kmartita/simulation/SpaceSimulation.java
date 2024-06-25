package com.kmartita.simulation;

import com.kmartita.config.HttpConfig;
import com.kmartita.data.http.RestHelper;
import com.kmartita.scenario.SpaceScenario;
import io.gatling.javaapi.core.Simulation;

import static io.gatling.javaapi.core.CoreDsl.*;

public class SpaceSimulation extends Simulation {

    @Override
    public void before() {
        RestHelper.clearSpaces();
    }

    {
        setUp(SpaceScenario.crudSpaceScenario()
                .injectOpen(atOnceUsers(3)))
                .protocols(HttpConfig.getHttpProtocol())
                .assertions(
                        global().successfulRequests().percent().gte(99.0),
                        global().responseTime().max().lte(5000));
    }
}
