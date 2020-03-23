package com.spintech.testtask.service.tmdb.model.paged;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.spintech.testtask.service.tmdb.model.entity.TmdbApiItem;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class PagedResponse <T extends TmdbApiItem> implements Serializable {

    private Long page;

    @JsonProperty("total_results")
    private Long totalResults;

    @JsonProperty("total_pages")
    private Long totalPages;

    private List<T> results = new ArrayList<>();

}
