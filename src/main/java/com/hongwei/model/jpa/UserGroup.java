package com.hongwei.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserGroup {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String user_name;

    @Column(nullable = false)
    private String role;

    public UserGroup() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}