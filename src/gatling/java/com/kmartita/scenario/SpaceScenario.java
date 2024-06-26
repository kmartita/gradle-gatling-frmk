package com.kmartita.scenario;

import com.kmartita.data.request.SpaceRequest;
import io.gatling.javaapi.core.ScenarioBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.scenario;

public class SpaceScenario {

    public static ScenarioBuilder crudSpaceScenario() {
        return scenario("CRUD space scenario")
                .exec(SpaceRequest.getEmptySpaces())
                .exec(SpaceRequest.createSpace())
                .exec(SpaceRequest.updateSpace())
                .exec(SpaceRequest.getSpaceById())
                .exec(SpaceRequest.deleteSpaceById())
                .pause(Duration.ofSeconds(1));
    }
}
