package dev.sumuks.Newspaper.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "headlines")
    private String headlines;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "url")
    private String url;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "website_name")
    private String websiteName;

    @Column(name = "category")
    private String category;

}
