package org.example.entity;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayer {
    private final List<String> playList;
    private int currentSongIndex;
    private @Getter String currentSong;

    public MusicPlayer() {
        this.playList = new ArrayList<>();
        this.currentSongIndex = 0;
        this.currentSong = "";
    }

    public int addSong(String song) {
        playList.add(song);
        return playList.size();
    }

    public String play() {
        if (playList.isEmpty()) {
            return "Playlist is empty";
        }

        currentSong = playList.get(currentSongIndex);
        return "Playing: " + currentSong;
    }

    public String pause() {
        if (!currentSong.isEmpty()) {
            currentSong = "";
            return "Paused";
        }

        return "Already paused";
    }

    public String next() {
        if (playList.isEmpty()) {
            return "Playlist is empty";
        }

        currentSongIndex = (currentSongIndex + 1) % playList.size();
        currentSong = playList.get(currentSongIndex);
        return "Playing next: " + currentSong;
    }

    public String previous() {
        if (playList.isEmpty()) {
            return "Playlist is empty";
        }

        currentSongIndex = (currentSongIndex - 1 + playList.size()) % playList.size();
        currentSong = playList.get(currentSongIndex);
        return "Playing previous: " + currentSong;
    }

    public String selectSong(String song) {
        int index = playList.indexOf(song);
        if (index == -1) {
            currentSongIndex = addSong(song) - 1;
            currentSong = song;
        } else {
            currentSongIndex = index;
            currentSong = playList.get(currentSongIndex);
        }

        return "Playing: " + currentSong;
    }
}
