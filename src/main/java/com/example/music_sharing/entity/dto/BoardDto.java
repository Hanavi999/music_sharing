package com.example.music_sharing.entity.dto;

import com.example.music_sharing.entity.Genres;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BoardDto {

    private String singer;

    private String song;

    private String info;

    private String link;

    private List<String> genres;

    public BoardDto() {
        this.genres = new ArrayList<>();
    }

}
