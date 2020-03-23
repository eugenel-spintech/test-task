package com.spintech.testtask.service;

import com.spintech.testtask.entity.Show;

public interface ShowService {
    Show registerShow(Show show);
    Show findShow(String showName);
    Show findShow(Long showId);
}

