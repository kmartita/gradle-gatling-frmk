package com.kmartita.data;

import com.google.gson.annotations.SerializedName;
import com.kmartita.data.endpoint.space.Spaces;
import com.kmartita.data.endpoint.team.Teams;
import com.kmartita.data.endpoint.space.model.Space;
import com.kmartita.data.endpoint.team.model.Team;
import lombok.Getter;

import java.util.List;

@Getter
public class Records<R> implements Teams, Spaces {

    @SerializedName("teams")
    private List<Team> teams;

    @SerializedName("spaces")
    private List<Space> spaces;
}
