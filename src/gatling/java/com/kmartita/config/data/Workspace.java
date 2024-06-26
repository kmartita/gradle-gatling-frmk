package com.kmartita.config.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Workspace {

    @Nonnull private String username;
}
