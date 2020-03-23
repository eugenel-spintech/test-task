package com.spintech.testtask.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String email;

    private String password;

    @ManyToMany
    @JoinTable(name = "user_show",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "show_id", referencedColumnName = "id")})
    private List<Show> shows = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_actor",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id", referencedColumnName = "id")})
    private List<Actor> favoriteActors = new ArrayList<>();
}