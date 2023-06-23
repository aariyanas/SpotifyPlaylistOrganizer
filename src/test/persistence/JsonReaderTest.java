package persistence;

import model.LikedSongs;
import model.Playlist;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Playlist playlist = reader.readPlaylist();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLikedSongs() {
        JsonReader reader = new JsonReader("./data/emptyLikedSongsList.json");
        try {
            LikedSongs likedSongs = reader.readLikedSongs();
            assertEquals("Liked Songs", likedSongs.getName());
        } catch (IOException e) {
            System.out.println(e);
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderLikedSongsList() {
        JsonReader reader = new JsonReader("./data/likedSongsList.json");
        try {
            LikedSongs likedSongs = reader.readLikedSongs();
            assertEquals("Liked Songs", likedSongs.getName());

            Song s0 = new Song("Law and Order: SVU theme song", "Mike Post", "TV and Film Scores", true);
            checkSong("Law and Order: SVU theme song", "Mike Post", "TV and Film Scores", true, s0);
            Song s1 = new Song("Bluebird", "Miranda Lambert", "Country", true);
            checkSong("Bluebird", "Miranda Lambert", "Country", true, s1);
            Song s2 = new Song("Who Knew", "P!nk", "Pop", true);
            checkSong("Who Knew", "P!nk", "Pop", true, s2);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyRapPlaylist() {
        JsonReader reader = new JsonReader("./data/emptyRapPlaylist.json");
        try {
            Playlist playlist = reader.readPlaylist();
            assertEquals("Rapz", playlist.getPlaylistName());
            assertEquals("Rap", playlist.getPlaylistGenre());
            assertEquals(0, playlist.getPlaylistLength());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderPopPunkPlaylist() {
        JsonReader reader = new JsonReader("./data/popPunkPlaylist.json");
        try {
            Playlist playlist = reader.readPlaylist();
            assertEquals("Pop Punk Vibe$", playlist.getPlaylistName());
            assertEquals("Pop-Punk", playlist.getPlaylistGenre());
            assertEquals(2, playlist.getPlaylistLength());

            List<Song> thingies = playlist.viewPlaylist(); //if i had/ve a method that gets all the songs in a playlist, here is where i would call it
            assertEquals(2, playlist.getPlaylistLength());

            Song s4 = new Song("Good 4 U", "Olivia Rodrigo", "Pop-Punk", true);
            checkSong("Good 4 U", "Olivia Rodrigo", "Pop-Punk", true, s4);

            Song s7 = new Song("Bloody Valentine", "Machine Gun Kelly", "Pop-Punk", true);
            checkSong("Bloody Valentine", "Machine Gun Kelly", "Pop-Punk", true, s7);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
