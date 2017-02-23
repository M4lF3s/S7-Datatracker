package de.mfraas.datatracker.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.mfraas.datatracker.data.bean.Project;
import de.mfraas.datatracker.data.repo.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by marcelfraas on 21.01.17.
 */
@RestController
public class ProjectController  {

    @Autowired
    private ProjectRepository projectRepository;

    @PostMapping("/project/new")
    public HttpStatus createNewProject(@RequestBody String s) {

        //TODO: Validate JSON String + Save Project in DB

        try{
            ObjectMapper mapper = new ObjectMapper();
            Project p = mapper.readValue(s, Project.class);
            projectRepository.save(p);
            return HttpStatus.OK;
        } catch(IOException e) {
            return HttpStatus.NOT_ACCEPTABLE;
        }
    }

    @PostMapping("/project")
    public HttpStatus loadProject(@RequestBody String s) {

        //TODO: Validate File and load data in DB

        System.out.println(s);
        return HttpStatus.OK;
    }

    @PostMapping("/project/uodate")
    public HttpStatus updateProject(String s) {

        //TODO: Validate JSON String + update Project in DB

        System.out.println(s);
        return HttpStatus.OK;
    }

    @GetMapping("/project")
    public ResponseEntity<?> getProject(){
        return new ResponseEntity<String>("{protokollführer: 'Marcel', speicherort: '/test/location'}", HttpStatus.OK);
    }

}
