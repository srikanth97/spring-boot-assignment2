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


    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        trackServiceImpl.saveTrack(track);
        responseEntity = new ResponseEntity("Succesfully created", HttpStatus.CREATED);
//        try{
//
//
//        }catch (TrackAlreadyExistsException ex){
//            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
//        }catch (Exception ex) {
//            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
//        }
        return responseEntity;
    }

    @GetMapping("track")
    public ResponseEntity<?> getTracks(){
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity(trackServiceImpl.getTracks(), HttpStatus.CREATED);
//        try{
//            responseEntity = new ResponseEntity(trackService.getTracks(), HttpStatus.CREATED);
//        }catch (Exception ex) {
//            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
//        }
        return responseEntity;
    }

    @PutMapping("track/{id}")
    public  ResponseEntity<?> updateTracks(@PathVariable(value = "id") int id,@Valid @RequestBody Track track) throws TrackNotFoundException, TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        Optional<Track> track1 = trackServiceImpl.getTrackById(id);
        track.setTrackId(id);
        System.out.println(track);
        trackServiceImpl.update(track);

        responseEntity = new ResponseEntity(trackServiceImpl.getTracks(), HttpStatus.CREATED);
        //        try{
//            Optional<Track> track1 = trackService.getTrackById(id);
//            track.setTrackId(id);
//            trackService.saveTrack(track);
//            responseEntity = new ResponseEntity(trackService.getTracks(), HttpStatus.CREATED);
//        }catch (TrackNotFoundException ex){
//            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
//        }catch (Exception ex) {
//            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
//          }
        return responseEntity;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTracks(@PathVariable("id") int id) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        trackServiceImpl.deleteTrack(id);
        responseEntity = new ResponseEntity(trackServiceImpl.getTracks(), HttpStatus.CREATED);
//        try{
//            trackService.deleteTrack(id);
//            responseEntity = new ResponseEntity(trackService.getTracks(), HttpStatus.CREATED);
//        }catch (TrackNotFoundException ex) {
//            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
//        }catch (Exception ex) {
//            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
//        }
        return responseEntity;
    }

    @GetMapping("track/{trackName}")
    public ResponseEntity<?> trackByName(@PathVariable("trackName") String name) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        List<Track> users = trackServiceImpl.trackByName(name);
        System.out.println("hjbdkkhasfdlkj");
        responseEntity = new ResponseEntity(users, HttpStatus.CREATED);
//        try{
//            List<Track> users = trackService.trackByName(name);
//            responseEntity = new ResponseEntity(users, HttpStatus.CREATED);
//        }catch (Exception ex) {
//            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
//        }
        return responseEntity;
    }

}
