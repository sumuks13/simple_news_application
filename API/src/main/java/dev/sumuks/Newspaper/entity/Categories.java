package dev.sumuks.Newspaper.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "categories")
public class Categories {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "category")
    private String category;

    @Column(name = "is_enabled")
    private boolean isEnabled;

}
