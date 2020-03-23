package com.spintech.testtask.entity.mapper.tmdb;

import com.spintech.testtask.entity.Show;
import com.spintech.testtask.service.tmdb.model.entity.TmdbShow;

public interface ShowTmdbMapper {
    Show map(TmdbShow tmdbShow);
}
