package com.api.marvel.challenge.web.controller;

import com.api.marvel.challenge.dto.MyPageable;
import com.api.marvel.challenge.persistence.integration.marvel.dto.CharacterDTO;
import com.api.marvel.challenge.service.character.ICharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/characters")
public class CharacterController {

    private final ICharacterService characterService;

    public CharacterController(ICharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int[] comics,
            @RequestParam(required = false) int[] series,
            @RequestParam(defaultValue = "0") long offset,
            @RequestParam(defaultValue = "10") long limit
    ) {

        MyPageable pageable = new MyPageable(offset, limit);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(characterService.findAll(pageable, name, comics, series));
    }

    @GetMapping("/{characterId}")
    public ResponseEntity<CharacterDTO.CharacterInfoDTO> findInfoById(
            @PathVariable Long characterId
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(characterService.findInfoById(characterId));
    }
}
