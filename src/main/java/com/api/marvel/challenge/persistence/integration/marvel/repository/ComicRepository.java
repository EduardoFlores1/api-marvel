package com.api.marvel.challenge.persistence.integration.marvel.repository;

import com.api.marvel.challenge.dto.MyPageable;
import com.api.marvel.challenge.persistence.integration.marvel.dto.ComicDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComicRepository {

    public List<ComicDTO> findAll(MyPageable pageable, Long characterId) {
        return null;
    }

    public ComicDTO findById(Long comicId) {
        return null;
    }
}
