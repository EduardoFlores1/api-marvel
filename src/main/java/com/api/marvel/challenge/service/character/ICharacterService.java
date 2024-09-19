package com.api.marvel.challenge.service.character;

import com.api.marvel.challenge.dto.MyPageable;
import com.api.marvel.challenge.persistence.integration.marvel.dto.CharacterDTO;

import java.util.List;

public interface ICharacterService {
    List<CharacterDTO> findAll(MyPageable pageable, String name, int[] comics, int[] series);

    CharacterDTO.CharacterInfoDTO findInfoById(Long characterId);
}
