package com.spintech.testtask.controller;

import com.spintech.testtask.entity.Actor;
import com.spintech.testtask.entity.Show;
import com.spintech.testtask.entity.User;
import com.spintech.testtask.entity.UserShow;
import com.spintech.testtask.entity.mapper.tmdb.ActorTmdbMapper;
import com.spintech.testtask.entity.mapper.tmdb.ShowTmdbMapper;
import com.spintech.testtask.service.ActorService;
import com.spintech.testtask.service.UserService;
import com.spintech.testtask.service.UserShowService;
import com.spintech.testtask.service.tmdb.TmdbApi;
import com.spintech.testtask.service.tmdb.model.entity.TmdbActor;
import com.spintech.testtask.service.tmdb.model.entity.TmdbShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActorService actorService;

    @Autowired
    private UserShowService userShowService;

    @Autowired
    private ActorTmdbMapper actorTmdbMapper;

    @Autowired
    private ShowTmdbMapper showTmdbMapper;

    @Autowired
    private TmdbApi tmdbApi;

    @RequestMapping(value = "/register", method = POST)
    public ResponseEntity registerUser(@RequestParam String email,
                                               @RequestParam String password) {
        if (userService.registerUser(email, password) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/{userId}/favorite_actor", method = POST)
    public ResponseEntity addFavoriteActor(@PathVariable Long userId, @RequestParam String actorFullName) {
        TmdbActor tmdbActor = tmdbApi.findActor(actorFullName);
        Actor actor = actorTmdbMapper.map(tmdbActor);
        User user = userService.findUser(userId);
        if(actor != null && user != null) {
            actorService.registerActor(actor);
            userService.addFavoriteActor(user, actor);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/{userId}/favorite_actor", method = DELETE)
    public ResponseEntity removeFavoriteActor(@PathVariable Long userId, @RequestParam String actorFullName) {
        Actor actor = actorService.findActor(actorFullName);
        User user = userService.findUser(userId);
        if(actor != null && user != null) {
            userService.removeFavoriteActor(user, actor);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/{userId}/show", method = POST)
    public ResponseEntity addShow(@PathVariable Long userId, @RequestParam String name) {
        TmdbShow tmdbShow = tmdbApi.findShow(name);
        Show show = showTmdbMapper.map(tmdbShow);
        User user = userService.findUser(userId);
        if(show != null && user != null) {
            userService.addShow(user, show);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/{userId}/show/{showId}/mark_as_watched", method = PUT)
    public ResponseEntity markAsWatched(@PathVariable Long userId, @PathVariable Long showId) {
        UserShow userShow = userShowService.findUserShow(userId, showId);
        if(userShow != null) {
            userShowService.markAsWatched(userShow);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/{userId}/show/{showId}/mark_as_unwatched", method = PUT)
    public ResponseEntity markAsUnwatched(@PathVariable Long userId, @PathVariable Long showId) {
        UserShow userShow = userShowService.findUserShow(userId, showId);
        if(userShow != null) {
            userShowService.markAsUnwatched(userShow);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
