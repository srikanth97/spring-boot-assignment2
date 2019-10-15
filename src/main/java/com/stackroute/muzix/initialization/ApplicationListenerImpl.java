package com.stackroute.muzix.initialization;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerImpl implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private TrackService trackService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            trackService.saveTrack(new Track(1,"default","fbajsb"));
        } catch (TrackAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
