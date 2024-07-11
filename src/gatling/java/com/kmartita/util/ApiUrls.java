package com.kmartita.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import static com.kmartita.config.ConfigReader.BASE_URL;
import static com.kmartita.util.ApiEndpoints.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiUrls {

    @Getter
    public static final String USER = getUrl(USER_PATH);
    @Getter
    public static final String TEAMS = getUrl(TEAMS_PATH);
    @Getter
    public static final String SPACES = getUrl(SPACES_PATH);
    @Getter
    public static final String SPACE = getUrl(SPACE_PATH);

    private static String convertString(String input) {
        String[] parts = input.split("\\{");

        var output = new StringBuilder();
        output.append(parts[0]);

        for (int i = 1; i < parts.length; i++) {
            output.append("${");
            output.append(parts[i]);
        }

        return output.toString();
    }

    private static String getUrl(String endpoint){
        return convertString(BASE_URL + endpoint.replaceFirst("/", StringUtils.EMPTY));
    }
}
