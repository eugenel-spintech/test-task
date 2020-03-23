package com.spintech.testtask.service.impl;

import com.spintech.testtask.entity.UserShow;
import com.spintech.testtask.repository.UserShowRepository;
import com.spintech.testtask.service.UserShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserShowServiceImpl implements UserShowService {

    @Autowired
    private UserShowRepository userShowRepository;

    @Override
    public UserShow findUserShow(Long userId, Long showId) {
        return userShowRepository.findByUserIdAndShowId(userId, showId);
    }

    @Override
    public UserShow markAsWatched(UserShow userShow) {
        userShow.setWatched(true);
        return userShowRepository.save(userShow);
    }

    @Override
    public UserShow markAsUnwatched(UserShow userShow) {
        userShow.setWatched(false);
        return userShowRepository.save(userShow);
    }

}