package com.api.marvel.challenge.persistence.integration.marvel.dto;

public record CharacterDTO(
        Long id,
        String name,
        String description,
        String modified,
        String resourceURI
) {
    public record CharacterInfoDTO(
            String imagePath,
            String description
    ) {

    }
}
