package com.spintech.testtask.repository;

import com.spintech.testtask.entity.Show;
import org.springframework.data.repository.CrudRepository;

public interface ShowRepository extends CrudRepository<Show, Long> {

    Show findByName(String name);
}