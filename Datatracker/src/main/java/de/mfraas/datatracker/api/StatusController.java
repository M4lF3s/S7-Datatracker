package de.mfraas.datatracker.api;

import de.mfraas.datatracker.data.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by marcelfraas on 18.01.17.
 */

@RestController
public class StatusController {

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/status")
    public ResponseEntity<?> status(){
        long count = projectRepository.count();
        if(count == 0){
            return new ResponseEntity<String>("NoProject", HttpStatus.OK);
        } else if(count == 1){
            // TODO: Check for Configuration
            return new ResponseEntity<String>("Normal", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Error", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/shutdown")
    public void shutdown() {
        SpringApplication.exit(appContext);
    }
}
