package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//unit tests for song class
class SongTest {

    Song s1;
    Song s2;
    Song s3;
    Song s4;
    Song s5;

    @BeforeEach
    void runBefore() {
        s1 = new Song("Easy On Me", "Adele", "Ballad", true);
        s2 = new Song("I Write Sins Not Tragedies", "Panic! At The Disco", "Emo", false);
        s3 = new Song("U + Ur Hand", "P!nk", "Pop", true);
        s4 = new Song("Good 4 U", "Olivia Rodrigo", "Pop-Punk", true);
        s5 = new Song("ME!", "Taylor Swift ft. Brendon Urie", "Pop", false);
    }

    @Test
    void testGetSongTitle() {
        assertEquals("Easy On Me", s1.getSongTitle());
        assertEquals("I Write Sins Not Tragedies", s2.getSongTitle());
        assertEquals("U + Ur Hand", s3.getSongTitle());
        assertEquals("Good 4 U", s4.getSongTitle());
        assertEquals("ME!", s5.getSongTitle());
    }

    @Test
    void testGetArtist() {
        assertEquals("Adele", s1.getSongArtist());
        assertEquals("Panic! At The Disco", s2.getSongArtist());
        assertEquals("P!nk", s3.getSongArtist());
        assertEquals("Olivia Rodrigo", s4.getSongArtist());
        assertEquals("Taylor Swift ft. Brendon Urie", s5.getSongArtist());
    }

    @Test
    void testGetGenre() {
        assertEquals("Ballad", s1.getSongGenre());
        assertEquals("Emo", s2.getSongGenre());
        assertEquals("Pop", s3.getSongGenre());
        assertEquals("Pop-Punk", s4.getSongGenre());
        assertEquals("Pop", s5.getSongGenre());
    }

    @Test
    void testSongIsLiked() {
        assertTrue(s1.getSongIsLiked());
        assertFalse(s2.getSongIsLiked());
        assertTrue(s3.getSongIsLiked());
        assertTrue(s4.getSongIsLiked());
        assertFalse(s5.getSongIsLiked());

    }
}


