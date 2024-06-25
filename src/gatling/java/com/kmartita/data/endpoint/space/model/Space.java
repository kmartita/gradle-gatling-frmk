package com.kmartita.data.endpoint.space.model;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class Space {

    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private boolean isPrivate;
    @Expose
    private boolean archived;
    @Expose
    private List<Status> statuses;
    @Expose
    private boolean multipleAssignees;
    @Expose
    private Features dueDates;
    @Expose
    private Features timeTracking;
    @Expose
    private Features tags;
    @Expose
    private Features timeEstimates;
    @Expose
    private Features checklists;
    @Expose
    private Features customFields;
    @Expose
    private Features remapDependencies;
    @Expose
    private Features dependencyWarning;
    @Expose
    private Features portfolios;
}
