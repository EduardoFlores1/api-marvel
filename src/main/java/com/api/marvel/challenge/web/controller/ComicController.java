package com.api.marvel.challenge.web.controller;

import com.api.marvel.challenge.dto.MyPageable;
import com.api.marvel.challenge.persistence.integration.marvel.dto.ComicDTO;
import com.api.marvel.challenge.service.comic.IComicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comics")
public class ComicController {

    private final IComicService comicService;

    public ComicController(IComicService comicService) {
        this.comicService = comicService;
    }

    @GetMapping
    public ResponseEntity<List<ComicDTO>> findAll(
            @RequestParam(required = false) Long characterId,
            @RequestParam(defaultValue = "0") long offset,
            @RequestParam(defaultValue = "10") long limit
    ) {
        MyPageable pageable = new MyPageable(offset, limit);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(comicService.findAll(pageable, characterId));
    }

    @GetMapping("/{comicId}")
    public ResponseEntity<ComicDTO> findById(
            @PathVariable Long comicId
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(comicService.findById(comicId));
    }
}
