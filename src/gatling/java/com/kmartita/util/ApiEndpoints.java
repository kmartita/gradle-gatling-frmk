package com.kmartita.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiEndpoints {

    @Getter
    public static final String USER_PATH = "/api/v2/user";
    @Getter
    public static final String TEAMS_PATH = "/api/v2/team";
    @Getter
    public static final String SPACES_PATH = "/api/v2/team/{team_id}/space";
    @Getter
    public static final String SPACE_PATH = "/api/v2/space/{space_id}";
}
