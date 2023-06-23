package persistence;

import model.LikedSongs;
import model.Playlist;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Playlist playlist = new Playlist("$o #ot F%@king Rock", "Rock", 0);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRapPlaylist() {
        try {
            Playlist playlist = new Playlist("Rapz", "Rap", 0);
            JsonWriter writer = new JsonWriter("./data/emptyRapPlaylist.json");
            writer.open();
            writer.writePlaylist(playlist);
            writer.close();

            JsonReader reader = new JsonReader("./data/emptyRapPlaylist.json");
            playlist = reader.readPlaylist();
            assertEquals("Rapz", playlist.getPlaylistName());
            assertEquals(0, playlist.getPlaylistLength());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyLikedSongsList() {
        try {
            LikedSongs likedSongs = new LikedSongs("Liked Songs");
            JsonWriter writer = new JsonWriter("./data/emptyLikedSongsList.json");
            writer.open();
            writer.writeLikedSongs(likedSongs);
            writer.close();

            JsonReader reader = new JsonReader("./data/emptyLikedSongsList.json");
            likedSongs = reader.readLikedSongs();
            assertEquals("Liked Songs", likedSongs.getName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterLikedSongsList() {
        try {
            LikedSongs likedSongs = new LikedSongs("Liked Songs");
            likedSongs.addToLikedSongs(new Song("Law and Order: SVU theme song", "Mike Post", "TV and Film Scores", true));
            likedSongs.addToLikedSongs(new Song("Bluebird", "Miranda Lambert", "Country", true));
            likedSongs.addToLikedSongs(new Song("Who Knew", "P!nk", "Pop", true));
            JsonWriter writer = new JsonWriter("./data/likedSongsList.json");
            writer.open();
            writer.writeLikedSongs(likedSongs);
            writer.close();

            JsonReader reader = new JsonReader("./data/likedSongsList.json");
            likedSongs = reader.readLikedSongs();
            assertEquals("Liked Songs", likedSongs.getName());
            Song s0 = new Song("Law and Order: SVU theme song", "Mike Post", "TV and Film Scores", true);
            checkSong("Law and Order: SVU theme song", "Mike Post", "TV and Film Scores", true, s0);
            Song s1 = new Song("Bluebird", "Miranda Lambert", "Country", true);
            checkSong("Bluebird", "Miranda Lambert", "Country", true, s1);
            Song s2 = new Song("Who Knew", "P!nk", "Pop", true);
            checkSong("Who Knew", "P!nk", "Pop", true, s2);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterPopPunkPlaylist() {
        try {
            Playlist playlist = new Playlist("Pop Punk Vibe$", "Pop-Punk", 4);
            playlist.addToPlaylist(new Song("Good 4 U", "Olivia Rodrigo", "Pop-Punk", true));
            playlist.addToPlaylist(new Song("Bloody Valentine", "Machine Gun Kelly", "Pop-Punk", true));
            JsonWriter writer = new JsonWriter("./data/popPunkPlaylist.json");
            writer.open();
            writer.writePlaylist(playlist);
            writer.close();

            JsonReader reader = new JsonReader("./data/popPunkPlaylist.json");
            playlist = reader.readPlaylist();
            assertEquals("Pop Punk Vibe$", playlist.getPlaylistName());
            List<Song> playlist2 = playlist.viewPlaylist(); //make a function that gets all the song in a playlist!!
            assertEquals(2, playlist.getPlaylistLength());
            assertEquals(2, playlist2.size());
            Song s4 = new Song("Good 4 U", "Olivia Rodrigo", "Pop-Punk", true);
            checkSong("Good 4 U", "Olivia Rodrigo", "Pop-Punk", true, s4);
            Song s7 = new Song("Bloody Valentine", "Machine Gun Kelly", "Pop-Punk", true);
            checkSong("Bloody Valentine", "Machine Gun Kelly", "Pop-Punk", true, s7);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
