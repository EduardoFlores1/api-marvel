package com.api.marvel.challenge.persistence.integration.marvel.repository;

import com.api.marvel.challenge.dto.MyPageable;
import com.api.marvel.challenge.persistence.integration.marvel.MarvelAPIConfig;
import com.api.marvel.challenge.persistence.integration.marvel.dto.CharacterDTO;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class CharacterRepository {

    private final MarvelAPIConfig marvelAPIConfig;

    public CharacterRepository(MarvelAPIConfig marvelAPIConfig) {
        this.marvelAPIConfig = marvelAPIConfig;
    }

    public List<CharacterDTO> findAll(MyPageable pageable, String name, int[] comics, int[] series) {
        Map<String, String> marvelQueryParams = getQueryParamsForFindAll(pageable, name, comics, series);

        //JsonNode response = httpClientService.doGet(characterPath, marvelQueryParams, JsonNode.class);

        //return CharacterMapper.toDtoList(response);
        return null;
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

    private String joinIntArray(int[] series) {
        List<String> stringArray = IntStream.of(series).boxed()
                .map(Object::toString)
                .toList();

        return String.join(",", stringArray);
    }

    public CharacterDTO.CharacterInfoDTO findInfoById(Long characterId) {
        return null;
    }
}
