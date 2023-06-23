package persistence;

import model.Song;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkSong(String songTitle, String songArtist, String songGenre, Boolean songIsLiked, Song song) {
        assertEquals(songTitle, song.getSongTitle());
        assertEquals(songArtist, song.getSongArtist());
        assertEquals(songGenre, song.getSongGenre());
        assertEquals(songIsLiked, song.getSongIsLiked());
    }
}
