package com.mustache.bbs.domain.entity.hospital;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Integer id;

    @Column
    private String nickname;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    public Review(String nickname, String content, Hospital hospital) {
        this.nickname = nickname;
        this.content = content;
        this.hospital = hospital;
    }
}
