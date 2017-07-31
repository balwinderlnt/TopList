package app.com.toplist.DTO.responses;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Balwinder Rajput on 27-07-2017.
 * Copyright (c) 2017 M800 inc. All rights reserved.
 */

public class TopListDTO {
    @SerializedName("artistName")
    private String artist;

    @SerializedName("name")
    private String songName;

    private String releaseDate;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @SerializedName("artworkUrl100")
    private String thumbnail;


}


