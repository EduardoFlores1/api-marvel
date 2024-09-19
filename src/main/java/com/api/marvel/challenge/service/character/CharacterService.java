package com.api.marvel.challenge.service.character;

import com.api.marvel.challenge.dto.MyPageable;
import com.api.marvel.challenge.persistence.integration.marvel.dto.CharacterDTO;
import com.api.marvel.challenge.persistence.integration.marvel.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService implements ICharacterService {

    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public List<CharacterDTO> findAll(MyPageable pageable, String name, int[] comics, int[] series) {
        return characterRepository.findAll(pageable, name, comics, series);
    }

    @Override
    public CharacterDTO.CharacterInfoDTO findInfoById(Long characterId) {
        return characterRepository.findInfoById(characterId);
    }
}
