package com.hoc.training.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    private String id;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "biodata_id", referencedColumnName = "id")
    private Biodata biodata;
}
