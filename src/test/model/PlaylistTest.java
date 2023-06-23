package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//unit tests for playlist class
public class PlaylistTest {

    Playlist p1;
    Playlist p2;
    Playlist p3;
    Playlist p4;
    Playlist p5;
    Playlist p6;
    Playlist p7;

    Song s1;
    Song s2;
    Song s3;
    Song s4;
    Song s5;
    Song s6;
    Song s7;
    Song s8;

    @BeforeEach
    void runBefore() {
        Song s1 = new Song("Easy On Me", "Adele", "Ballad", true);
        Song s2 = new Song("I Write Sins Not Tragedies", "Panic! At The Disco", "Emo", false);
        Song s3 = new Song("U + Ur Hand", "P!nk", "Pop", true);
        Song s4 = new Song("Good 4 U", "Olivia Rodrigo", "Pop-Punk", true);
        Song s5 = new Song("ME!", "Taylor Swift ft. Brendon Urie", "Pop", false);
        Song s6 = new Song("That's What You Get", "Paramore", "Pop-Punk", true);
        Song s7 = new Song("Bloody Valentine", "Machine Gun Kelly", "Pop-Punk", true);
        Song s8 = new Song("Dance Dance", "Fall Out Boy", "Pop-Punk", false);

        //p1 = new Playlist("Raps", "Rap", 100);
        p1 = new Playlist("S O U L F U L  V O C A L Z", "Ballad", 1);
        p2 = new Playlist("S O U L F U L  V O C A L Z", "Ballad", 1);
        p2.addToPlaylist(s1);
        p3 = new Playlist("", "", 0);
        //p4 = new Playlist("Pop Punk Vibe$", "Pop-Punk", 1, List<Song>(p4));
        //p4.add(s4);
        p5 = new Playlist("Pop Punk Vibe$", "Pop-Punk", 1);
        p5.addToPlaylist(s4);
        p5.addToPlaylist(s6);
        p6 = new Playlist("Pop Punk Vibe$", "Pop-Punk", 1);
        p6.addToPlaylist(s4);
        p6.addToPlaylist(s6);
        p6.addToPlaylist(s8);
        p7 = new Playlist("Pop Punk Vibe$", "Pop-Punk", 1);
        p7.addToPlaylist(s4);
        p7.addToPlaylist(s6);
        p7.addToPlaylist(s3);
    }

    @Test
    void testGetName() {
        //assertEquals("Raps", p1.getPlaylistName());
        assertEquals("S O U L F U L  V O C A L Z", p2.getPlaylistName());
        assertEquals("", p3.getPlaylistName());
        assertEquals("Pop Punk Vibe$", p5.getPlaylistName());
    }

    @Test
    void testGetPlaylistGenre() {
        //assertEquals("Rap", p1.getPlaylistGenre());
        assertEquals("Ballad", p2.getPlaylistGenre());
        assertEquals("", p3.getPlaylistGenre());
        assertEquals("Pop-Punk", p5.getPlaylistGenre());
    }

    @Test
    void testGetLength() {
        //assertEquals(100, p1.getPlaylistLength());
        assertEquals(1, p2.getPlaylistLength());
        assertEquals(0, p3.getPlaylistLength());
        assertEquals(2, p5.getPlaylistLength());
    }

    @Test
    void testSongWasAddedToPlaylist() {
        assertTrue(p2.songWasAddedToPlaylist(s1));
        assertFalse(p5.songWasAddedToPlaylist(s8));
        assertFalse(p5.songWasAddedToPlaylist(s3));
        assertFalse(p5.songWasAddedToPlaylist(s5));
    }

    @Test
    void testSeeCurrentlyInPlaylist(){
        assertEquals(p1.getPlaylist(), p1.viewPlaylist());
        assertEquals(p7.getPlaylist(), p7.viewPlaylist());
    }

}

