package com.kmartita.data.http;

import com.kmartita.config.ConfigReader;
import com.kmartita.data.endpoint.space.Spaces;
import com.kmartita.data.endpoint.space.model.Space;
import com.kmartita.data.endpoint.team.Teams;
import com.kmartita.data.endpoint.team.model.Member;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static java.lang.String.*;
import static java.util.Collections.*;

@Slf4j
public class RestHelper {

    private static final RestApi REST_API = RestApiProvider.getRestApi(RestApi.class);

    private static Teams getTeamResponse() {
        return ApiCaller.execute(REST_API.getTeamsQuery(ConfigReader.TOKEN), OK.code()).body();
    }

    public static String getTeamId(){
        String username = Objects.requireNonNull(ConfigReader.getConfig().getWorkspace().getUsername(),
                "'workspace.username' property is not provided");

        return getTeamResponse().getTeams()
                .stream()
                .filter(team -> team.getMembers()
                        .stream()
                        .map(Member::getUser)
                        .anyMatch(user -> username.equals(user.getUsername())))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(format("Team with %s not found", username)))
                .getId();
    }

    private static Spaces getSpaceResponse() {
        return ApiCaller.execute(REST_API.getSpacesQuery(getTeamId(), ConfigReader.TOKEN), OK.code()).body();
    }

    private static void deleteSpaceResponse(String id) {
        ApiCaller.execute(REST_API.deleteSpaceQuery(id, ConfigReader.TOKEN), OK.code());
    }

    public static void clearSpaces() {
        Spaces spaces = RestHelper.getSpaceResponse();
        if (!spaces.getSpaces().isEmpty()) {
            deleteSpaces(spaces.getSpaces());
        }
    }

    private static void deleteSpaces(List<Space> spaces) {
        spaces.forEach(space -> {
            try {
                deleteSpaceResponse(space.getId());
            } catch (Exception e) {
                for (String s : singletonList(format("Cannot delete space with id: %s. See details. Details: %s", space.getId(), e.getMessage()))) {
                    log.warn(s);
                }
            }
        });
    }
}
