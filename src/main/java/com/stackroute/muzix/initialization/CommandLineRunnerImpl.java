package com.stackroute.muzix.initialization;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Autowired
    private TrackService trackService;

    @Override
    public void run(String... args) throws Exception {
        try {
            trackService.saveTrack(new Track(2,"command liner","fbajsxkncb"));
        } catch (TrackAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
