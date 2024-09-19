package com.api.marvel.challenge.service.comic;

import com.api.marvel.challenge.dto.MyPageable;
import com.api.marvel.challenge.persistence.integration.marvel.dto.ComicDTO;

import java.util.List;

public interface IComicService {
    List<ComicDTO> findAll(MyPageable pageable, Long characterId);

    ComicDTO findById(Long comicId);
}
