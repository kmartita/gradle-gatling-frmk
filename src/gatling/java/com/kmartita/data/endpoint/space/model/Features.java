package com.kmartita.data.endpoint.space.model;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Features {

    @Expose
    private boolean enabled;
}
