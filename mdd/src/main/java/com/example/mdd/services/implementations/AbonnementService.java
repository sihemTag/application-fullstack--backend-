package com.example.mdd.services.implementations;

import com.example.mdd.dto.responses.AbonnementDTO;
import com.example.mdd.dto.responses.UserDTO;
import com.example.mdd.exceptions.NotFoundException;
import com.example.mdd.mappers.AbonnementMapper;
import com.example.mdd.mappers.UserMapper;
import com.example.mdd.models.*;
import com.example.mdd.repositories.AbonnementRepository;
import com.example.mdd.repositories.ThemeRepository;
import com.example.mdd.services.interfaces.AbonnementInterface;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AbonnementService implements AbonnementInterface {

    private final AbonnementRepository abonnementRepository;
    private final ThemeRepository themeRepository;
    private final UserService userService;
    private final AbonnementMapper abonnementMapper;
    private final UserMapper userMapper;

    public AbonnementService(AbonnementRepository abonnementRepository, ThemeRepository themeRepository, UserService userService, AbonnementMapper abonnementMapper, UserMapper userMapper) {
        this.abonnementRepository = abonnementRepository;
        this.themeRepository = themeRepository;
        this.userService = userService;
        this.abonnementMapper = abonnementMapper;
        this.userMapper = userMapper;
    }

    @Override
    public void subscribe(Integer themeId, Authentication authentication) throws NotFoundException {
        ThemeEntity themeEntity = themeRepository.findById(themeId)
                .orElseThrow(() -> new NotFoundException("Theme not found!"));

        UserDTO userDTO = userService.currentUserName(authentication)
                .orElseThrow(() -> new NotFoundException("No user authenticated found!"));
        UserEntity userEntity = userMapper.toEntity(userDTO);

        AbonnementEntity abonnement = new AbonnementEntity();
        abonnement.setTheme(themeEntity);
        abonnement.setUser(userEntity);
        abonnementRepository.save(abonnement);
    }

    @Override
    public void unsubscribe(Integer themeId, Authentication authentication){
        List<AbonnementDTO> abonnements = getAbonnements(authentication); //stream
        for(AbonnementDTO abonnement : abonnements){
            if(Objects.equals(abonnement.getTheme().getId(), themeId)){
                abonnementRepository.deleteById(abonnement.getId());
            }
        }
    }

    @Override
    public List<AbonnementDTO> getAbonnements(Authentication authentication) {
        UserDTO userDTO = userService.currentUserName(authentication)
                .orElseThrow(() -> new IllegalArgumentException("No user authenticated found!"));

        List<AbonnementEntity> abonnements = abonnementRepository.findAll();

        return abonnements.stream()
                .filter(abonnement -> Objects.equals(abonnement.getUser().getId(), userDTO.getId()))
                .map(abonnementMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isSubscribed(Integer themeId, Authentication authentication){
       List<AbonnementDTO> abonnements = getAbonnements(authentication);
        for(AbonnementDTO abonnement : abonnements){
            if(abonnement.getTheme().getId() == themeId){
                return true;
            }
        }
        return false;
    }
}
