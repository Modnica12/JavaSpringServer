package com.mp.auth;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
    @Id
    private String username;

    private Integer hash;

    public User(){
    }

    public Integer getHash() {
        return hash;
    }

    public String getUsername() {
        return username;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
