package com.spintech.testtask.entity.mapper.tmdb.impl;

import com.spintech.testtask.entity.Actor;
import com.spintech.testtask.entity.mapper.tmdb.ActorTmdbMapper;
import com.spintech.testtask.service.tmdb.model.entity.TmdbActor;
import org.springframework.stereotype.Service;

@Service
public class ActorTmdbMapperImpl implements ActorTmdbMapper {

    @Override
    public Actor map(TmdbActor tmdbActor) {
        return Actor.builder()
                   .fullName(tmdbActor.getName())
                   .tmdbId(tmdbActor.getId())
                   .build();
    }
}
