package com.bcit.streamerdex;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Stream implements Parcelable {
    private String streamerName;
    private String streamTitle;
    private String streamLink;
    private String streamDescription;
    private String videoLink;
    private ArrayList<String> tags;

    public Stream() {}

    public Stream(String name, String title, String link, String description, String videoLink, ArrayList<String> streamTags) {
        streamerName = name;
        streamTitle = title;
        streamLink = link;
        streamDescription = description;
        this.videoLink = videoLink;
        tags = streamTags;
    }

    protected Stream(Parcel in) {
        streamerName = in.readString();
        streamTitle = in.readString();
        streamLink = in.readString();
        streamDescription = in.readString();
        videoLink = in.readString();
        tags = in.createStringArrayList();
    }

    public static final Creator<Stream> CREATOR = new Creator<Stream>() {
        @Override
        public Stream createFromParcel(Parcel in) {
            return new Stream(in);
        }

        @Override
        public Stream[] newArray(int size) {
            return new Stream[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(streamerName);
        dest.writeString(streamTitle);
        dest.writeString(streamLink);
        dest.writeString(streamDescription);
        dest.writeString(videoLink);
        dest.writeStringList(tags);
    }
}
