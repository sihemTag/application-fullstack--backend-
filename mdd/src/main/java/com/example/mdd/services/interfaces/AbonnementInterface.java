package com.example.mdd.services.interfaces;

import com.example.mdd.dto.responses.AbonnementDTO;
import com.example.mdd.exceptions.NotFoundException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AbonnementInterface {
    public void subscribe(Integer themeId, Authentication authentication) throws NotFoundException;
    public void unsubscribe(Integer themeId, Authentication authentication);
    public List<AbonnementDTO> getAbonnements(Authentication authentication);
    public boolean isSubscribed(Integer themeId, Authentication authentication);
}
