package com.tutorials.app.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@ApiModel("Details about Tutorial")
public class TutorialDto {
    @ApiModelProperty("Tutorial ID")
    private Long id;
    @ApiModelProperty("Tutorial title")
    private String title;
    @ApiModelProperty("Tutorial description")
    private String description;
    @ApiModelProperty("published books")
    private boolean published;
}
