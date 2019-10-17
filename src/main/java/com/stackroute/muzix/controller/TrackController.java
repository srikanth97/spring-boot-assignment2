package com.stackroute.muzix.controller;


import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.service.TrackService;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1")
public class TrackController {

    @Autowired
    @Qualifier("trackServiceImpl")
    TrackService trackServiceImpl;


    @PostMapping("add")
    public ResponseEntity<?> save(@RequestBody Track track) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        trackServiceImpl.saveTrack(track);
        responseEntity = new ResponseEntity("Succesfully created", HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity(trackServiceImpl.getTracks(), HttpStatus.CREATED);
        return responseEntity;
    }

    @PutMapping("update/{id}")
    public  ResponseEntity<?> update(@PathVariable(value = "id") int id,@Valid @RequestBody Track track) throws TrackNotFoundException, TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        Optional<Track> track1 = trackServiceImpl.getTrackById(id);
        track.setTrackId(id);
        System.out.println(track);
        trackServiceImpl.update(track);
        responseEntity = new ResponseEntity(trackServiceImpl.getTracks(), HttpStatus.CREATED);
        return responseEntity;
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        trackServiceImpl.deleteTrack(id);
        responseEntity = new ResponseEntity(trackServiceImpl.getTracks(), HttpStatus.CREATED);
        return responseEntity;
    }

    @GetMapping("track/{trackName}")
    public ResponseEntity<?> getByName(@PathVariable("trackName") String name) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        List<Track> users = trackServiceImpl.trackByName(name);
        responseEntity = new ResponseEntity(users, HttpStatus.CREATED);
        return responseEntity;
    }

}
