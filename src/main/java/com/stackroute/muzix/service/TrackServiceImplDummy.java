package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import com.stackroute.muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("trackSesrviceImplDummy")
//@Primary
public class TrackServiceImplDummy implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImplDummy(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        System.out.println("dummy");
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
        System.out.println("dummy");
        return trackRepository.findAll();
    }

    @Override
    public Track update(Track track) {
        return null;
    }


    @Override
    public boolean deleteTrack(int trackId) throws TrackNotFoundException {
        System.out.println("dummy");
        getTrackById(trackId);
        trackRepository.deleteById(trackId);
        return true;
    }

    @Override
    public Optional<Track> getTrackById(int id) throws TrackNotFoundException {
        System.out.println("dummy");
        if(!trackRepository.findById(id).isPresent()){
            throw new TrackNotFoundException("track with "+id+"does not exists in the  database");
        }
        return trackRepository.findById(id);
    }

    @Override
    public List<Track> trackByName(String name) {
        System.out.println("dummy");
        return trackRepository.trackByName(name);
    }
}
