package com.spintech.testtask.service;

import com.spintech.testtask.entity.Actor;

public interface ActorService {
    Actor registerActor(Actor actor);
    Actor findActor(String fullName);
}

