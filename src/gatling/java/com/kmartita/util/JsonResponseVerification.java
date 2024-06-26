package com.kmartita.util;
import io.gatling.javaapi.core.CoreDsl;
import static io.gatling.javaapi.core.CheckBuilder.*;

public class JsonResponseVerification {

    public static Final verifyResponseId() {
        return CoreDsl.jsonPath("$.id").exists();
    }
}
