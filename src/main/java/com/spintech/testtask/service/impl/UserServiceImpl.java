package com.spintech.testtask.service.impl;

import com.spintech.testtask.entity.Actor;
import com.spintech.testtask.entity.Show;
import com.spintech.testtask.entity.User;
import com.spintech.testtask.repository.ActorRepository;
import com.spintech.testtask.repository.ShowRepository;
import com.spintech.testtask.repository.UserRepository;
import com.spintech.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ShowRepository showRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User registerUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (Objects.nonNull(user)) {
            return null;
        }
        user = User.builder().email(email).password(passwordEncoder.encode(password)).build();
        return userRepository.save(user);
    }

    @Override
    public User findUser(String email, String password) {
        User foundUser = userRepository.findByEmail(email);
        if (Objects.nonNull(foundUser)) {
            if (passwordEncoder.matches(password, foundUser.getPassword())) {
                return foundUser;
            }
        }
        return null;
    }

    @Override
    public User findUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void addFavoriteActor(User user, Actor actor) {
        Actor savedActor = actorRepository.save(actor);
        user.getFavoriteActors().add(savedActor);
        userRepository.save(user);
    }

    @Override
    public void removeFavoriteActor(User user, Actor actor) {
        user.getFavoriteActors().remove(actor);
        userRepository.save(user);
    }

    @Override
    public void addShow(User user, Show show) {
        Show savedShow = showRepository.save(show);
        user.getShows().add(savedShow);
        userRepository.save(user);
    }
}