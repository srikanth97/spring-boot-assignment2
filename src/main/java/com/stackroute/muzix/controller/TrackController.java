package com.stackroute.muzix.controller;


import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<?> saveTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try{
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity("Succesfully created", HttpStatus.CREATED);
        }catch (Exception ex) {
            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("track")
    public ResponseEntity<?> getTracks(){
        ResponseEntity responseEntity;
        try{
            responseEntity = new ResponseEntity(trackService.getTracks(), HttpStatus.CREATED);
        }catch (Exception ex) {
            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("track/{id}")
    public  ResponseEntity<?> updateTracks(@PathVariable(value = "id") int id,@Valid @RequestBody Track track){
        ResponseEntity responseEntity;
        Optional<Track> track1 = trackService.getTrackById(id);
        try{
            if(!track1.isPresent()){
                throw new Exception("id-"+id);
            }
            track.setTrackId(id);
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity(trackService.getTracks(), HttpStatus.CREATED);
        }catch (Exception ex) {
            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTracks(@PathVariable("id") int id){
        ResponseEntity responseEntity;
        try{
            trackService.deleteTrack(id);
            responseEntity = new ResponseEntity(trackService.getTracks(), HttpStatus.CREATED);
        }catch (Exception ex) {
            responseEntity = new ResponseEntity(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;

    }
}
