package com.blog.blog.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class JandiDto implements Serializable {
    private final Long jandiNo;
    @JsonIgnore
    private final UserDto userNo;
    private final Integer jandiCount;
    private final LocalDate jandiDate;
}
