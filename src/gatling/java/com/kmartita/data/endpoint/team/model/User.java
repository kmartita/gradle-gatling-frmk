package com.kmartita.data.endpoint.team.model;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class User {

    @Expose
    private long id;
    @Expose
    private String username;
    @Expose
    private String email;
    @Expose
    private String color;
    @Expose
    private String profilePicture;
    @Expose
    private String initials;
    @Expose
    private int role;
    @Expose
    private String customRole;
    @Expose
    private String lastActive;
    @Expose
    private String dateJoined;
    @Expose
    private String dateInvited;
}
