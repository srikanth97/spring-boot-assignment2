package com.stackroute.muzix.initialization;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exception.TrackAlreadyExistsException;
import com.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@PropertySource("application.properties")
public class ApplicationListenerImpl implements ApplicationListener<ContextRefreshedEvent> {
    @Qualifier("trackServiceImpl")
    @Autowired
    private TrackService trackService;

    @Value("${track.trackId}")
    private int trackId;

    @Value("${track.trackName}")
    private String trackName;

    @Value("${track.comments}")
    private String comments;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            trackService.saveTrack(new Track(trackId,trackName,comments));
        } catch (TrackAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
