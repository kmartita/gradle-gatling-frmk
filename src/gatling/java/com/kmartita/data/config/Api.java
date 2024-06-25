package com.kmartita.data.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Api {

    @Nonnull private String url;
    @Nonnull private String token;
}
