package com.spintech.testtask.entity.mapper.tmdb;

import com.spintech.testtask.entity.Actor;
import com.spintech.testtask.service.tmdb.model.entity.TmdbActor;

public interface ActorTmdbMapper {
    Actor map(TmdbActor tmdbActor);
}
