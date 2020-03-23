package com.spintech.testtask.service.tmdb;

import com.spintech.testtask.service.tmdb.model.entity.TmdbActor;
import com.spintech.testtask.service.tmdb.model.entity.TmdbShow;

public interface TmdbApi {
    String popularTVShows();
    TmdbActor findActor(String fullName);
    TmdbShow findShow(String fullName);
}
