package com.example.mdd.controllers;

import com.example.mdd.dto.requests.ThemeRequest;
import com.example.mdd.dto.responses.ThemeDTO;
import com.example.mdd.services.implementations.ThemeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/themes")
public class ThemeController {

    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @PostMapping
    public void addTheme(@RequestBody ThemeRequest themeRequest){
        themeService.addTheme(themeRequest.getTitle(),themeRequest.getDescription());
    }

    @DeleteMapping("{id}")
    public void deleteThemeById(@PathVariable Integer id){
        themeService.deleteThemeById(id);
    }

    @GetMapping
    public ResponseEntity<List<ThemeDTO>> getAllThemes() {
        List<ThemeDTO> themes = themeService.getAllThemes();
        return ResponseEntity.ok(themes);
    }
}
