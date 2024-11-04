package com.fengshui.common.repository.postgresql.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatResponse {
    private List<Choice> choices;

    // constructors, getters and setters
}
