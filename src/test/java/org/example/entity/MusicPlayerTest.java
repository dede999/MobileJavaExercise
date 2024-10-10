package org.example.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicPlayerTest {
    private MusicPlayer musicPlayer;

    @BeforeEach
    void setUp() {
        musicPlayer = new MusicPlayer();
    }

    @Test
    void addSong() {
        assertEquals(1, musicPlayer.addSong("Song 1"));
        assertEquals(2, musicPlayer.addSong("Song 2"));
        assertEquals(3, musicPlayer.addSong("Song 3"));
    }

    @Test
    void playSongInEmptyPlaylist() {
        assertEquals("Playlist is empty", musicPlayer.play());
    }

    @Test
    void playSongInAPlaylistWithElements() {
        musicPlayer.addSong("Song 1");

        assertEquals("Playing: Song 1", musicPlayer.play());
        musicPlayer.pause();
        assertEquals("Playing: Song 1", musicPlayer.play());
    }

    @Test
    void pause() {
        musicPlayer.addSong("Song 1");

        assertEquals("Already paused", musicPlayer.pause());
        musicPlayer.play();
        assertEquals("Paused", musicPlayer.pause());
    }

    @Test
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
    void selectPresentSong() {
        musicPlayer.addSong("Present");

        assertEquals("Playing: Present", musicPlayer.selectSong("Present"));
    }

    @Test
    void selectAbsentSong() {
        assertEquals("Playing: Absent", musicPlayer.selectSong("Absent"));
    }

    @Test
    void getCurrentSongWhenPaused() {
        assertEquals("", musicPlayer.getCurrentSong());
    }

    @Test
    void getCurrentSongWhenPlaying() {
        musicPlayer.addSong("Song 1");
        musicPlayer.play();

        assertEquals("Song 1", musicPlayer.getCurrentSong());
    }
}