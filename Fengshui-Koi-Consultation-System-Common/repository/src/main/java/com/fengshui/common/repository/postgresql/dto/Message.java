package com.fengshui.common.repository.postgresql.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Message {

    private String role;
    private String content;

    // constructor, getters and setters
}