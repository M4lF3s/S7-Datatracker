package de.mfraas.fxclient.services;

import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by marcelfraas on 21.01.17.
 */
public class CommunicationsService {

    private static final String baseURI = "http://localhost:4200";

    public static ResponseEntity<String> status() throws ResourceAccessException {
        final String uri = baseURI + "/status";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<String>("parameters");
        return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
    }

    public static void shutdown() throws ResourceAccessException {
        final String uri = baseURI + "/shutdown";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, null, String.class);
    }


}
