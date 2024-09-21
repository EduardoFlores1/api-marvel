package com.api.marvel.challenge.service.httpClient;

import com.api.marvel.challenge.exception.ApiErrorException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class RestTemplateService implements HttpClientService {

    private final RestTemplate restTemplate;

    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <T> T doGet(String endPoint, Map<String, String> queryParams, Class<T> responseType) {
        String finalUrl = buildFinalUrl(endPoint, queryParams);

        HttpEntity httpEntity = new HttpEntity(getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.GET, httpEntity, responseType);

        if(response.getStatusCode().value() != HttpStatus.OK.value()) {
            String message = String.format("Error consumiendo endpoint [ {} - {} ], código de respuesta es: {}",
                    HttpMethod.GET, endPoint, response.getStatusCode());
            throw new ApiErrorException(message);
        }
        return response.getBody();
    }

    private static String buildFinalUrl(String endPoint, Map<String, String> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(endPoint);
        if(queryParams != null) {
            for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }
        return builder.build().toString();
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Override
    public <T, R> T doPost(String endPoint, Map<String, String> queryParams, Class<T> responseType, R bodyRequest) {
        String finalUrl = buildFinalUrl(endPoint, queryParams);

        HttpEntity httpEntity = new HttpEntity(bodyRequest, getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.POST, httpEntity, responseType);

        if(response.getStatusCode().value() != HttpStatus.OK.value() || response.getStatusCode().value() != HttpStatus.CREATED.value()) {
            String message = String.format("Error consumiendo endpoint [ {} - {} ], código de respuesta es: {}",
                    HttpMethod.POST, endPoint, response.getStatusCode());
            throw new ApiErrorException(message);
        }
        return response.getBody();
    }

    @Override
    public <T, R> T doPut(String endPoint, Map<String, String> queryParams, Class<T> responseType, R bodyRequest) {
        String finalUrl = buildFinalUrl(endPoint, queryParams);

        HttpEntity httpEntity = new HttpEntity(bodyRequest, getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.PUT, httpEntity, responseType);

        if(response.getStatusCode().value() != HttpStatus.OK.value()) {
            String message = String.format("Error consumiendo endpoint [ {} - {} ], código de respuesta es: {}",
                    HttpMethod.PUT, endPoint, response.getStatusCode());
            throw new ApiErrorException(message);
        }
        return response.getBody();
    }

    @Override
    public <T> T doDelete(String endPoint, Map<String, String> queryParams, Class<T> responseType) {
        String finalUrl = buildFinalUrl(endPoint, queryParams);

        HttpEntity httpEntity = new HttpEntity(getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.DELETE, httpEntity, responseType);

        if(response.getStatusCode().value() != HttpStatus.OK.value()) {
            String message = String.format("Error consumiendo endpoint [ {} - {} ], código de respuesta es: {}",
                    HttpMethod.DELETE, endPoint, response.getStatusCode());
            throw new ApiErrorException(message);
        }
        return response.getBody();
    }
}
