package com.kmartita.data.endpoint.space.model;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Status {

    @Expose
    private String status;
    @Expose
    private String type;
    @Expose
    private int orderindex;
    @Expose
    private String color;
}
