package com.spintech.testtask.service;

import com.spintech.testtask.entity.UserShow;

public interface UserShowService {
    UserShow findUserShow(Long userId, Long showId);
    UserShow markAsWatched(UserShow userShow);
    UserShow markAsUnwatched(UserShow userShow);
}

