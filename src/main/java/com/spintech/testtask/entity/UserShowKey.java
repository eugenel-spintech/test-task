package com.spintech.testtask.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserShowKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "show_id")
    private Long showId;


}