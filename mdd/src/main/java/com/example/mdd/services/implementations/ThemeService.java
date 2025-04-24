package com.example.mdd.services.implementations;

import com.example.mdd.dto.responses.ThemeDTO;
import com.example.mdd.mappers.ThemeMapper;
import com.example.mdd.models.ThemeEntity;
import com.example.mdd.repositories.ThemeRepository;
import com.example.mdd.services.interfaces.ThemeInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThemeService implements ThemeInterface {

    private final ThemeRepository themeRepository;
    private final ThemeMapper themeMapper;

    public ThemeService(ThemeRepository themeRepository, ThemeMapper themeMapper) {
        this.themeRepository = themeRepository;
        this.themeMapper = themeMapper;
    }

    @Override
    public void addTheme(String title, String description){
        ThemeEntity themeEntity = new ThemeEntity(title,description);
        themeRepository.save(themeEntity);
    }

    @Override
    public void deleteThemeById(Integer id){
        themeRepository.deleteById(id);
    }

    @Override
    public List<ThemeDTO> getAllThemes() {
        return themeRepository.findAll()
                .stream()
                .map(themeMapper::toDTO)
                .collect(Collectors.toList());
    }

}
