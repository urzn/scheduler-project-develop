package com.example.schedulerprojectdevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id") // FK
    private User user;

    public Schedule(){}

    public Schedule(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

    public void setUser(User user){ this.user = user;}
}
