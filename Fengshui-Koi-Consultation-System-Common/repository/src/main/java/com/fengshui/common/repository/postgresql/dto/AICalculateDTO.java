package com.fengshui.common.repository.postgresql.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AICalculateDTO {
    String description;
    String element;
    Integer score;

    @JsonCreator
    public AICalculateDTO(
            @JsonProperty("description") String description,
            @JsonProperty("element") String element,
            @JsonProperty("score") Integer score) {
        this.description = description;
        this.element = element;
        this.score = score;
    }
}
