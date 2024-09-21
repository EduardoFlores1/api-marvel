package com.api.marvel.challenge.persistence.integration.marvel.repository;

import com.api.marvel.challenge.dto.MyPageable;
import com.api.marvel.challenge.persistence.integration.marvel.MarvelAPIConfig;
import com.api.marvel.challenge.persistence.integration.marvel.dto.CharacterDTO;
import com.api.marvel.challenge.service.httpClient.HttpClientService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class CharacterRepository {

    private final MarvelAPIConfig marvelAPIConfig;
    private final HttpClientService httpClientService;

    @Value("${integration.marvel.base-path}")
    private String basePath;
    private String characterPath;

    public CharacterRepository(MarvelAPIConfig marvelAPIConfig, HttpClientService httpClientService) {
        this.marvelAPIConfig = marvelAPIConfig;
        this.httpClientService = httpClientService;
    }

    @PostConstruct
    private void setPath() {
        this.characterPath = this.basePath.concat("/").concat("characters");
    }

    public List<CharacterDTO> findAll(MyPageable pageable, String name, int[] comics, int[] series) {
        Map<String, String> marvelQueryParams = getQueryParamsForFindAll(pageable, name, comics, series);

        JsonNode response = httpClientService.doGet(characterPath, marvelQueryParams, JsonNode.class);

        return null;
        //return CharacterMapper.toDtoList(response);
    }

    private Map<String, String> getQueryParamsForFindAll(MyPageable pageable, String name, int[] comics, int[] series) {
        Map<String, String> marvelQueryParams = marvelAPIConfig.getAuthenticationQueryParams();

        marvelQueryParams.put("offset", Long.toString(pageable.offset()));
        marvelQueryParams.put("limit", Long.toString(pageable.limit()));

        if(StringUtils.hasText(name)) {
            marvelQueryParams.put("name", name);
        }

        if (comics != null && comics.length > 0) {
            String comicsAsString = this.joinIntArray(comics);
            marvelQueryParams.put("comics", comicsAsString);
        }

        if (series != null && series.length > 0) {
            String seriesAsString = this.joinIntArray(series);
            marvelQueryParams.put("comics", seriesAsString);
        }

        return marvelQueryParams;
    }

    private String joinIntArray(int[] arr) {
        return IntStream.of(arr)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));
    }

    public CharacterDTO.CharacterInfoDTO findInfoById(Long characterId) {
        Map<String, String> marvelQueryParams = marvelAPIConfig.getAuthenticationQueryParams();

        String finalUrl = characterPath.concat("/").concat(Long.toString(characterId));

        JsonNode response = httpClientService.doGet(finalUrl, marvelQueryParams, JsonNode.class);

        return null;
        //return CharacterMapper.toDtoList(response).get(0);
    }
}
