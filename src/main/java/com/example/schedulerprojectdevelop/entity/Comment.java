package com.example.schedulerprojectdevelop.entity;

import lombok.Getter;
import jakarta.persistence.*;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "longtext")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "schedule_id") // FK
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(String contents, Schedule schedule){
        this.contents = contents;
        this.schedule = schedule;
    }

    public Comment() {

    }
}
