package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.exception.TrackNotFoundException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public List<Track> getTracks();

    public Track update(Track track);

    public boolean deleteTrack(int trackId) throws TrackNotFoundException;

    public Optional<Track> getTrackById(int id) throws TrackNotFoundException;

    public List<Track> trackByName(String name) throws TrackNotFoundException;
}
