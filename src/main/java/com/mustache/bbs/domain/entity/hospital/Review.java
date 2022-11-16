package com.mustache.bbs.domain.entity.hospital;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ToString
@Getter
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String nickname;

    @Column
    private String content;

    @Column(name = "hospital_id")
    private Integer hospitalId;

}
