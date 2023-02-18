package ru.yzuykov.jsandbox.application.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRq {

    private String id;
    private String text;
    private Integer age;
}
