package com.api.marvel.challenge.service.comic;

import com.api.marvel.challenge.dto.MyPageable;
import com.api.marvel.challenge.persistence.integration.marvel.dto.ComicDTO;
import com.api.marvel.challenge.persistence.integration.marvel.repository.ComicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicService implements IComicService{

    private final ComicRepository comicRepository;

    public ComicService(ComicRepository comicRepository) {
        this.comicRepository = comicRepository;
    }

    @Override
    public List<ComicDTO> findAll(MyPageable pageable, Long characterId) {
        return comicRepository.findAll(pageable, characterId);
    }

    @Override
    public ComicDTO findById(Long comicId) {
        return comicRepository.findById(comicId);
    }
}
