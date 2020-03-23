package com.spintech.testtask.repository;

import com.spintech.testtask.entity.UserShow;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserShowRepository extends CrudRepository<UserShow, Long> {

    @Query("SELECT us FROM UserShow us "
            + " WHERE us.userShowKey.userId = :userId AND us.userShowKey.showId = :showId")
    UserShow findByUserIdAndShowId(@Param("userId") Long userId, @Param("showId") Long showId);
}