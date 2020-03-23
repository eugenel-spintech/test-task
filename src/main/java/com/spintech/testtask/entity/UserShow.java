package com.spintech.testtask.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_show")
public class UserShow {

    @EmbeddedId
    private UserShowKey userShowKey;

    private Boolean watched = false;

    public UserShow(User user, Show show, Boolean watched) {
        userShowKey = new UserShowKey(user.getId(), show.getId());
        this.watched = watched;
    }
}