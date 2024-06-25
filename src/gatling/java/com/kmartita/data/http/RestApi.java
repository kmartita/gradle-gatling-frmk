package com.kmartita.data.http;

import com.kmartita.data.Records;
import com.kmartita.data.endpoint.space.model.Space;
import com.kmartita.data.endpoint.team.model.Team;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

import static com.kmartita.util.Constant.AUTHORIZATION;
import static com.kmartita.util.ApiPathConstant.*;

public interface RestApi {

    @GET(TEAMS_PATH)
    Call<Records<Team>> getTeamsQuery(@Header(AUTHORIZATION) String authorization);

    @GET(SPACES_PATH)
    Call<Records<Space>> getSpacesQuery(@Path("team_id") String teamId, @Header(AUTHORIZATION) String authorization);

    @DELETE(SPACE_PATH)
    Call<Space> deleteSpaceQuery(@Path("space_id") String id, @Header(AUTHORIZATION) String authorization);

}
