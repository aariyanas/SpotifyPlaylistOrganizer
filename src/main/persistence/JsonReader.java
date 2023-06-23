package persistence;

import com.sun.org.apache.xpath.internal.operations.Bool;
import model.LikedSongs;
import model.Playlist;
import model.Song;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

//Represents a reader that reads playlist from JSON data stored in file
public class JsonReader {
    private String source;

    //EFFECTS: constructs a reader to read data from the source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads playlist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Playlist readPlaylist() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlaylist(jsonObject);
    }

    //EFFECTS: reads the liked songs list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public LikedSongs readLikedSongs() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLikedSongs(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // throws IOException if an error occurs reading data from file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses playlist from JSON object and returns it
    private Playlist parsePlaylist(JSONObject jsonObject) {
        String name = jsonObject.getString("playlistName");
        String genre = jsonObject.getString("playlistGenre");
        int length = jsonObject.getInt("playlistLength");
        Playlist playlist = new Playlist(name, genre, length);
        addPlaylistSongs(playlist, jsonObject);
        return playlist;
    }

    // EFFECTS: parses liked songs from JSON object and returns it
    private LikedSongs parseLikedSongs(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray likedSongsArray = jsonObject.getJSONArray("liked songs");
        LikedSongs likedSongs = new LikedSongs(name);
        for (Object likedSong : likedSongsArray) {
            JSONObject likedSongData = (JSONObject) likedSong;
            String title = likedSongData.getString("songTitle");
            String artist = likedSongData.getString("songArtist");
            String genre = likedSongData.getString("songGenre");
            Boolean liked = ((JSONObject) likedSong).getBoolean("songIsLiked");
            likedSongs.addToLikedSongs(new Song(title, artist, genre, liked));
            System.out.println(likedSongData);
        }
        return likedSongs;
    }

    // EFFECTS: parses songs from JSON object and adds them to playlist
    private void addPlaylistSongs(Playlist playlist, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Playlist Songs");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addSongToPlaylist(playlist, nextSong);
        }
    }

    // MODIFIES: playlist
    // EFFECTS: parses song from JSON object and adds it to playlist
    private void addSongToPlaylist(Playlist playlist, JSONObject jsonObject) {
        String name = jsonObject.getString("songTitle");
        String artist = jsonObject.getString("songArtist");
        String genre = jsonObject.getString("songGenre");
        Boolean liked = jsonObject.getBoolean("songIsLiked");
        Song song = new Song(name, artist, genre, liked);
        playlist.addToPlaylist(song);
    }
}
