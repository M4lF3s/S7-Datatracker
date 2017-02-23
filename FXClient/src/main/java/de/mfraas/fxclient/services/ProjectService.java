package de.mfraas.fxclient.services;

import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.HashMap;

/**
 * Created by marcelfraas on 22.01.17.
 */
public class ProjectService {

    private final static String baseURI = "http://localhost:4200/project";


    public static String createNewProject(HashMap<String, String> data) {
        //System.out.println(new JSONObject(data));


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>((new JSONObject(data)).toString(),headers);

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForObject(baseURI+"/new", (new JSONObject(data)).toString(), String.class);


        /*
        final String uri = baseURI + "/project";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri, (new JSONObject(data)).toString(), String.class);
        */

    }

    public static String loadProject(File file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        HttpEntity<String> entity = new HttpEntity<String>(file.getAbsolutePath(),headers);

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForObject(baseURI, file.getAbsolutePath(), String.class);
    }

    public static String updateProject(HashMap<String, String> newData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>((new JSONObject(newData)).toString(),headers);

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForObject(baseURI+"/update", (new JSONObject(newData)).toString(), String.class);
    }

    public static String getProject() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<String>("parameters");
        return restTemplate.exchange(baseURI, HttpMethod.GET, entity, String.class).getBody();
    }
}
