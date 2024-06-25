package com.kmartita.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import static com.kmartita.config.ConfigReader.BASE_URL;
import static com.kmartita.util.ApiPathConstant.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestConstant {

    @Getter
    public static final String TEAMS = convertString(BASE_URL + TEAMS_PATH.replaceFirst("/", StringUtils.EMPTY));
    @Getter
    public static final String SPACES = convertString(BASE_URL + SPACES_PATH.replaceFirst("/", StringUtils.EMPTY));
    @Getter
    public static final String SPACE = convertString(BASE_URL + SPACE_PATH.replaceFirst("/", StringUtils.EMPTY));

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
}
