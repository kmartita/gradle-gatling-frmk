package com.kmartita.data.endpoint.team.model;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class Team {

    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String color;
    @Expose
    private String avatar;
    @Expose
    private List<Member> members;
}
