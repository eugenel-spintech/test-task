package com.spintech.testtask.service.impl;

import com.spintech.testtask.entity.Actor;
import com.spintech.testtask.repository.ActorRepository;
import com.spintech.testtask.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Override
    public Actor registerActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public Actor findActor(String fullName) {
        return actorRepository.findByFullName(fullName);
    }
}