package com.spintech.testtask.service;

import com.spintech.testtask.entity.Actor;
import com.spintech.testtask.entity.Show;
import com.spintech.testtask.entity.User;

public interface UserService {
    User registerUser(String email, String password);
    User findUser(String email, String password);
    User findUser(Long userId);

    void addFavoriteActor(User user, Actor actor);
    void removeFavoriteActor(User user, Actor actor);

    void addShow(User user, Show show);
}

