package com.kmartita.util;

import io.gatling.javaapi.core.CheckBuilder;
import io.gatling.javaapi.core.CoreDsl;

public class Verification {

    public static CheckBuilder.Final verifyResponseId() {
        return CoreDsl.jsonPath("$.id").exists();
    }
}
