package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.existsById(track.getTrackId())){
            throw new TrackAlreadyExistsException("Track already exists");
        }
        Track track1 = trackRepository.save(track);
        if(track1 == null){
            throw new TrackAlreadyExistsException("Track already exists");
        }
        return track1;
    }

    @Override
    public List<Track> getTracks() {
        return trackRepository.findAll();
    }



    @Override
    public boolean deleteTrack(int trackId) throws TrackNotFoundException {
        getTrackById(trackId);
        trackRepository.deleteById(trackId);
        return true;
    }

    @Override
    public Optional<Track> getTrackById(int id) throws TrackNotFoundException {
        if(!trackRepository.findById(id).isPresent()){
            throw new TrackNotFoundException("track with "+id+"does not exists in the  database");
        }
       return trackRepository.findById(id);
    }

    @Override
    public List<Track> trackByName(String name) {
        return trackRepository.trackByName(name);
    }
}
