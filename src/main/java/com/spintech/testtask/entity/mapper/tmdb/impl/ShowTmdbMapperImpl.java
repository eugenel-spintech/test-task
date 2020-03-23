package com.spintech.testtask.entity.mapper.tmdb.impl;

import com.spintech.testtask.entity.Show;
import com.spintech.testtask.entity.mapper.tmdb.ShowTmdbMapper;
import com.spintech.testtask.service.tmdb.model.entity.TmdbShow;
import org.springframework.stereotype.Service;

@Service
public class ShowTmdbMapperImpl implements ShowTmdbMapper {

    @Override
    public Show map(TmdbShow tmdbShow) {
        return Show.builder()
                   .name(tmdbShow.getName())
                   .description(tmdbShow.getOverview())
                   .tmdbId(tmdbShow.getId())
                   .build();
    }
}
