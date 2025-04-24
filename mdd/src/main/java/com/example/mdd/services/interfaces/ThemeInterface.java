package com.example.mdd.services.interfaces;

import com.example.mdd.dto.responses.ThemeDTO;

import java.util.List;

public interface ThemeInterface {
    public void addTheme(String title, String description);
    public void deleteThemeById(Integer id);
    public List<ThemeDTO> getAllThemes();
}
