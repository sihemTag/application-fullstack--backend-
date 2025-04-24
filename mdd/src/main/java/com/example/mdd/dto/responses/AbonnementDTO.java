package com.example.mdd.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbonnementDTO {

    private Integer id;

    @JsonProperty("theme")
    private ThemeDTO theme;

    @JsonProperty("user")
    private UserDTO user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ThemeDTO getTheme() {
        return theme;
    }

    public void setTheme(ThemeDTO theme) {
        this.theme = theme;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}