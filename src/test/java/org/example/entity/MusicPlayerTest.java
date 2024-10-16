package org.example.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicPlayerTest {
    private MusicPlayer musicPlayer;

    @BeforeEach
    void setUp() {
        musicPlayer = new MusicPlayer();
    }

    @Test
    @DisplayName("Add a song to the playlist")
    void addSong() {
        assertEquals(1, musicPlayer.addSong("Song 1"));
        assertEquals(2, musicPlayer.addSong("Song 2"));
        assertEquals(3, musicPlayer.addSong("Song 3"));
    }

    @Test
    @DisplayName("Plying a song in an empty playlist")
    void playSongInEmptyPlaylist() {
        assertEquals("Playlist is empty", musicPlayer.play());
    }

    @Test
    @DisplayName("Playing a song in a playlist with elements")
    void playSongInAPlaylistWithElements() {
        musicPlayer.addSong("Song 1");

        assertEquals("Playing: Song 1", musicPlayer.play());
        musicPlayer.pause();
        assertEquals("Playing: Song 1", musicPlayer.play());
    }

    @Test
    @DisplayName("Pausing a song")
    void pause() {
        musicPlayer.addSong("Song 1");

        assertEquals("Already paused", musicPlayer.pause());
        musicPlayer.play();
        assertEquals("Paused", musicPlayer.pause());
    }

    @Test
    @DisplayName("Skipping a song")
    void next() {
        assertEquals("Playlist is empty", musicPlayer.next());

        musicPlayer.addSong("Song 1");
        musicPlayer.addSong("Song 2");
        musicPlayer.addSong("Song 3");

        assertEquals("Playing next: Song 2", musicPlayer.next());
        assertEquals("Playing next: Song 3", musicPlayer.next());
        assertEquals("Playing next: Song 1", musicPlayer.next());
    }

    @Test
    @DisplayName("Playing the previous song")
    void previous() {
        assertEquals("Playlist is empty", musicPlayer.previous());

        musicPlayer.addSong("Song 1");
        musicPlayer.addSong("Song 2");
        musicPlayer.addSong("Song 3");

        assertEquals("Playing previous: Song 3", musicPlayer.previous());
        assertEquals("Playing previous: Song 2", musicPlayer.previous());
        assertEquals("Playing previous: Song 1", musicPlayer.previous());
    }

    @Test
    @DisplayName("Selecting a song")
    void selectPresentSong() {
        musicPlayer.addSong("Present");

        assertEquals("Playing: Present", musicPlayer.selectSong("Present"));
    }

    @Test
    @DisplayName("Selecting an absent song")
    void selectAbsentSong() {
        assertEquals("Playing: Absent", musicPlayer.selectSong("Absent"));
    }

    @Test
    @DisplayName("Getting the current song when paused")
    void getCurrentSongWhenPaused() {
        assertEquals("", musicPlayer.getCurrentSong());
    }

    @Test
    @DisplayName("Getting the current song when playing")
    void getCurrentSongWhenPlaying() {
        musicPlayer.addSong("Song 1");
        musicPlayer.play();

        assertEquals("Song 1", musicPlayer.getCurrentSong());
    }
}