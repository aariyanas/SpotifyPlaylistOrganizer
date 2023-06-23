package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Represents a Playlist that has a specific genre and initial length (number of songs)
public class Playlist implements Writable {
    private String playlistName;
    private String playlistGenre;
    private int playlistLength;
    private List<Song> playlist;


    //EFFECTS: constructs a playlist with a specific name, genre and length
    //REQUIRES: name and genre are strings; length is an int
    public Playlist(String name, String genre, int length) {
        playlistName = name;
        playlistGenre = genre;
        playlistLength = length;
        playlist = new ArrayList<>();
    }

    //EFFECTS: gets the name of the playlist
    public String getPlaylistName() {
        return playlistName;
    }

    //EFFECTS: gets the playlist's genre
    public String getPlaylistGenre() {
        return playlistGenre;
    }

    //EFFECTS: gets the current length of the playlist
    public int getPlaylistLength() {
        return playlist.size();
    }

    //EFFECTS: gets the songs that are in the playlist
    public List<Song> getPlaylist() {
        return playlist;
    }

    //EFFECTS: add a song to a playlist
    //REQUIRES: song genre matches playlist genre
    //          song is in liked songs
    //MODIFIES: this
    public List<Song> addToPlaylist(Song song) {
        if (song.getSongIsLiked() && (song.getSongGenre().equals(playlistGenre))) {
            playlist.add(song);
            playlistLength++;
        }
        EventLog.getInstance().logEvent(new Event("Song added to playlist."));
        return playlist;
    }

    //EFFECTS: returns an unmodifiable list of songs that are currently in the playlist
    //REQUIRES: a playlist to exist
    //MODIFIES: nothing
    public List<Song> viewPlaylist() {
        return Collections.unmodifiableList(playlist);
    }

    //EFFECTS: check is a song was added to the playlist (true if yes/false otherwise)
    //REQUIRES: song is added to playlist
    //MODIFIES: this
    public boolean songWasAddedToPlaylist(Song song) {
        return playlist.contains(song);
    }

    //EFFECTS: turns a playlist into a Json object
    //MODIFIES: playlist
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("playlistName", playlistName);
        json.put("playlistGenre", playlistGenre);
        json.put("playlistLength", playlistLength);
        json.put("Playlist Songs", songsToJson());
        return json;
    }

    //EFFECTS: returns songs in this playlist as a JSON array
    //MODIFIES: playlist
    private JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Song s : playlist) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }
}

