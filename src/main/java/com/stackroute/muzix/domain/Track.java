package com.stackroute.muzix.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Track {
    @Id
    private int trackId;
    private String trackName;
    private String comments;

//    public Track() {
//
//    }

//    @Autowired
//    public Track(@Value("${track.trackId}") int trackId, @Value("${track.trackName}") String trackName,@Value("${track.comments}") String comments) {
//        this.trackId = trackId;
//        this.trackName = trackName;
//        this.comments = comments;
//    }
//
////    public Track(int trackId, String trackName, String comments) {
////        this.trackId = trackId;
////        this.trackName = trackName;
////        this.comments = comments;
////    }
//
//    public int getTrackId() {
//        return trackId;
//    }
//
//    public void setTrackId(int trackId) {
//        this.trackId = trackId;
//    }
//
//    public String getTrackName() {
//        return trackName;
//    }
//
//    public void setTrackName(String trackName) {
//        this.trackName = trackName;
//    }
//
//    public String getComments() {
//        return comments;
//    }
//
//    public void setComments(String comments) {
//        this.comments = comments;
//    }
//
//    @Override
//    public String toString() {
//        return "Track{" +
//                "trackId=" + trackId +
//                ", trackName='" + trackName + '\'' +
//                ", comments='" + comments + '\'' +
//                '}';
//    }
}
