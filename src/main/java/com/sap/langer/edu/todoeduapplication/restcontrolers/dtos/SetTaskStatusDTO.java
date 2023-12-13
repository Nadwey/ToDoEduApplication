package com.sap.langer.edu.todoeduapplication.restcontrolers.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SetTaskStatusDTO
{
    @JsonProperty("status")
    private String status;
}
