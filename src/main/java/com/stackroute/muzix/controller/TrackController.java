package com.stackroute.muzix.controller;


import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.service.TrackService;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1")
public class TrackController {

    TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws TrackAlreadyExistsException {
        ResponseEntity responseEntity;
        trackService.saveTrack(track);
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
        responseEntity = new ResponseEntity(trackService.getTracks(), HttpStatus.CREATED);
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
        Optional<Track> track1 = trackService.getTrackById(id);
        track.setTrackId(id);
        trackService.saveTrack(track);
        responseEntity = new ResponseEntity(trackService.getTracks(), HttpStatus.CREATED);
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
        trackService.deleteTrack(id);
        responseEntity = new ResponseEntity(trackService.getTracks(), HttpStatus.CREATED);
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

    @PostMapping("track/{trackName}")
    public ResponseEntity<?> trackByName(@PathVariable("trackName") String name) {
        ResponseEntity responseEntity;
        List<Track> users = trackService.trackByName(name);
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
