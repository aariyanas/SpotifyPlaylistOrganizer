package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.EventLog;

//A list that represents a user's liked songs
public class LikedSongs implements Writable {
    private String name;
    private ArrayList<Song> likedSongs;

    //EFFECTS: constructs a list of a user's liked songs with a specific name
    //REQUIRES: name is a string
    public LikedSongs(String name) {
        this.name = name;
        likedSongs = new ArrayList<>();
    }

    //EFFECTS: gets the name of the liked songs list
    public String getName() {
        return name;
    }

    //EFFECTS: adds a song to the user's list of liked songs
    //REQUIRES: songIsLiked == true
    //MODIFIES: this
    public ArrayList<Song> addToLikedSongs(Song song) {
        if (song.getSongIsLiked()) {
            likedSongs.add(song);
        }
        EventLog.getInstance().logEvent(new Event("Song added to liked songs."));
        return likedSongs;
    }

    //EFFECTS: checks if the song was added to the liked songs list (true if yes/false otherwise)
    //REQUIRES: song is added to user's liked songs
    //MODIFIES: this
    public boolean songWasAddedToLiked(Song song) {
        return likedSongs.contains(song);
    }

    //EFFECTS: remove a song to the user's list of liked songs
    //REQUIRES: songIsLiked == false
    //MODIFIES: this
    public ArrayList<Song> removeFromLikedSongs(Song song) {
        if (!song.getSongIsLiked()) {
            likedSongs.remove(song);
        }
        EventLog.getInstance().logEvent(new Event("Song removed from liked songs"));
        return likedSongs;
    }

    //EFFECTS: checks if the song was successfully removed from the liked songs list (true if yes/false otherwise)
    //REQUIRES: song is removed from user's liked songs
    //MODIFIES: this
    public boolean songWasRemoved(Song song) {
        return !likedSongs.contains(song);
    }

    public ArrayList<Song> getLikedSongs() {
        return likedSongs;
    }

    //EFFECTS: turns the liked songs list into a JSon object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("liked songs", songsToJson());
        return json;
    }

    public List<String> toList() {
        List<String> list = new ArrayList();
        for (Song s : likedSongs) {
            list.add(s.toString());
        }
        return list;
    }

    private JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Song s : likedSongs) {
            jsonArray.put(s.toJson());
        }
        return jsonArray;
    }
}