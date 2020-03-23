package com.spintech.testtask.service.tmdb.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spintech.testtask.entity.Actor;
import com.spintech.testtask.service.tmdb.TmdbApi;
import com.spintech.testtask.service.tmdb.model.entity.TmdbActor;
import com.spintech.testtask.service.tmdb.model.entity.TmdbShow;
import com.spintech.testtask.service.tmdb.model.paged.ActorPagedResponse;
import com.spintech.testtask.service.tmdb.model.paged.ShowPagedResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

@Service
@Slf4j
public class TmdbApiImpl implements TmdbApi {
    @Value("${tmdb.apikey}")
    private String tmdbApiKey;
    @Value("${tmdb.language}")
    private String tmdbLanguage;
    @Value("${tmdb.api.base.url}")
    private String tmdbApiBaseUrl;

    private ObjectMapper jsonMapper = new ObjectMapper();

    public String popularTVShows() throws IllegalArgumentException {
        try {
            String url = getTmdbUrl("/tv/popular");
            return queryString(url);
        } catch (URISyntaxException e) {
            log.error("Couldn't get popular tv shows", e);
        }
        return null;
    }

    public TmdbActor findActor(String fullName) throws IllegalArgumentException {
        try {
            String url = getTmdbUrl("/search/person", fullName);
            ActorPagedResponse pagedResponse = queryObject(url, ActorPagedResponse.class);

            return pagedResponse != null ? pagedResponse.getResults().get(0) : null;
        } catch (URISyntaxException | JsonProcessingException e) {
            log.error("Couldn't get an actor", e);
        }
        return null;
    }

    public TmdbShow findShow(String name) throws IllegalArgumentException {
        try {
            String url = getTmdbUrl("/search/tv", name);
            ShowPagedResponse pagedResponse = queryObject(url, ShowPagedResponse.class);

            return pagedResponse != null ? pagedResponse.getResults().get(0) : null;
        } catch (URISyntaxException | JsonProcessingException e) {
            log.error("Couldn't get a show", e);
        }
        return null;
    }

    private String getTmdbUrl(String tmdbItem) throws URISyntaxException {
        return constructTmdbUriBuilder(tmdbItem).build().toString();
    }

    private String getTmdbUrl(String tmdbItem, String query) throws URISyntaxException {
        URIBuilder uriBuilder = constructTmdbUriBuilder(tmdbItem);
        uriBuilder.addParameter("query", query);
        return uriBuilder.build().toString();
    }

    private URIBuilder constructTmdbUriBuilder(String tmdbItem) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(tmdbApiBaseUrl + tmdbItem);
        uriBuilder.addParameter("language", tmdbLanguage);
        uriBuilder.addParameter("api_key", tmdbApiKey);
        return uriBuilder;
    }

    private String queryString(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            return null;
        }

        return response.getBody();
    }

    private <T> T queryObject(String url, Class<T> clazz) throws JsonProcessingException {
        String response = queryString(url);
        return response != null ? jsonMapper.readValue(response, clazz) : null;
    }
}
