package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//unit tests for liked songs class
public class LikedSongsTest {
    LikedSongs l1;
    LikedSongs l2;
    LikedSongs l3;

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

        l1 = new LikedSongs("Liked Songs");
        l1.addToLikedSongs(s1);

        l2 = new LikedSongs("Liked Songs");
        l2.addToLikedSongs(s1);
        l2.addToLikedSongs(s3);

        l3 = new LikedSongs("Liked Songs");
        l3.addToLikedSongs(s1);
        l3.addToLikedSongs(s2);
        l3.addToLikedSongs(s3);
    }

    @Test
    void testGetName() {
        assertEquals("Liked Songs", l1.getName());
    }

    @Test
    void testSongWasAddedToLiked() {
        assertTrue(l1.songWasAddedToLiked(s1));
        assertTrue(l2.songWasAddedToLiked(s3));
        assertFalse(l3.songWasAddedToLiked(s2));
    }

    @Test
    void testSongWasRemoved() {
        l3.removeFromLikedSongs(s2);
        l3.removeFromLikedSongs(s1);

        assertTrue(l3.songWasRemoved(s2));
        assertFalse(l3.songWasRemoved(s1));
    }

    @Test
    void testViewLikedSongs(){

    }
}


