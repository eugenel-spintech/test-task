package com.spintech.testtask.service.impl;

import com.spintech.testtask.entity.Show;
import com.spintech.testtask.repository.ShowRepository;
import com.spintech.testtask.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowServiceImpl implements ShowService {

    @Autowired
    private ShowRepository showRepository;

    @Override
    public Show registerShow(Show show) {
        return showRepository.save(show);
    }

    @Override
    public Show findShow(String name) {
        return showRepository.findByName(name);
    }

    @Override
    public Show findShow(Long showId) {
        return showRepository.findById(showId).orElse(null);
    }

}