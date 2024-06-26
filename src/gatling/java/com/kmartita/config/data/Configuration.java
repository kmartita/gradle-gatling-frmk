package com.kmartita.config.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Configuration {

    private Api api;
    private Workspace workspace;
}
