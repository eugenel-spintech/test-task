package com.spintech.testtask.service.tmdb.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TmdbActor implements TmdbApiItem {

    private Long id;
    private String name;

}
