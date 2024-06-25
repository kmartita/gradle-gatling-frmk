package com.kmartita.data.endpoint.team.model;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Member {

    @Expose
    private User user;
}
