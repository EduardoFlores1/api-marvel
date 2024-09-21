package com.api.marvel.challenge.persistence.integration.marvel.repository;

import com.api.marvel.challenge.dto.MyPageable;
import com.api.marvel.challenge.persistence.integration.marvel.MarvelAPIConfig;
import com.api.marvel.challenge.persistence.integration.marvel.dto.ComicDTO;
import com.api.marvel.challenge.service.httpClient.HttpClientService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ComicRepository {

    private final MarvelAPIConfig marvelAPIConfig;
    private final HttpClientService httpClientService;

    @Value("${integration.marvel.base-path}")
    private String basePath;
    private String comicPath;

    public ComicRepository(MarvelAPIConfig marvelAPIConfig, HttpClientService httpClientService) {
        this.marvelAPIConfig = marvelAPIConfig;
        this.httpClientService = httpClientService;
    }

    @PostConstruct
    private void setPath() {
        this.comicPath = this.basePath.concat("/").concat("comics");
    }

    public List<ComicDTO> findAll(MyPageable pageable, Long characterId) {
        Map<String, String> marvelQueryParams = getQueryParamsForFindAll(pageable, characterId);

        JsonNode response = httpClientService.doGet(comicPath, marvelQueryParams, JsonNode.class);

        return null;
        //return ComicMapper.toDtoList(response);
    }

    private Map<String, String> getQueryParamsForFindAll(MyPageable pageable, Long characterId) {
        Map<String, String> marvelQueryParams = marvelAPIConfig.getAuthenticationQueryParams();

        marvelQueryParams.put("offset", Long.toString(pageable.offset()));
        marvelQueryParams.put("limit", Long.toString(pageable.limit()));

        if (characterId != null && characterId > 0) {
            marvelQueryParams.put("characterId", Long.toString(characterId));
        }

        return marvelQueryParams;
    }

    public ComicDTO findById(Long comicId) {
        Map<String, String> marvelQueryParams = marvelAPIConfig.getAuthenticationQueryParams();

        String finalUrl = comicPath.concat("/").concat(Long.toString(comicId));

        JsonNode response = httpClientService.doGet(finalUrl, marvelQueryParams, JsonNode.class);

        return null;
        //return ComicMapper.toDtoList(response).get(0);
    }
}
