package com.bcit.streamerdex.stream_cards;

import java.util.ArrayList;

public class Stream {
    private String streamerName;
    private String streamTitle;
    private String streamLink;
    private String streamDescription;
    private String videoLink;
    private ArrayList<String> tags;

    public Stream(String name, String title, String link, String description, String videoLink, ArrayList<String> streamTags) {
        streamerName = name;
        streamTitle = title;
        streamLink = link;
        streamDescription = description;
        this.videoLink = videoLink;
        tags = streamTags;
    }

    public String getStreamerName() {
        return streamerName;
    }

    public String getStreamTitle() {
        return streamTitle;
    }

    public String getStreamLink() {
        return streamLink;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getStreamDescription() {
        return streamDescription;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setStreamerName(String streamerName) {
        this.streamerName = streamerName;
    }

    public void setStreamTitle(String streamTitle) {
        this.streamTitle = streamTitle;
    }

    public void setStreamLink(String streamLink) {
        this.streamLink = streamLink;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public void setStreamDescription(String streamDescription) {
        this.streamDescription = streamDescription;
    }


    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }
}
