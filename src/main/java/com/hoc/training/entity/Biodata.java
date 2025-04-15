package com.hoc.training.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "biodata")
public class Biodata {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "comment")
    private List<Comment> comments;
}
