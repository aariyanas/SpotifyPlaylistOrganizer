package model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;

//Represents a song that has a specific title and artist who sings it
public class Song implements Writable {
    private boolean songIsLiked;   //true if the user has liked the song
    private String songTitle;      //title of the song
    private String songArtist;     //who sings the song
    private String songGenre;      //genre of the song

    /* REQUIRES: songTitle has a non-zero length;
                 artist has a non-zero length;
    // EFFECTS: constructs a song with title songTitle, singer artist and genre genre;
     */
    public Song(String title, String artist, String genre, Boolean liked) {
        songTitle = title;
        songArtist = artist;
        songGenre = genre;
        songIsLiked = liked;
        EventLog.getInstance().logEvent(new Event("New song created."));
    }

    //EFFECTS: gets the song's title
    public String getSongTitle() {
        return songTitle;
    }

    //EFFECTS: gets the song's artist
    public String getSongArtist() {
        return songArtist;
    }

    //EFFECTS: gets the song's genre
    public String getSongGenre() {
        return songGenre;
    }

    //EFFECTS: tells us whether the user has liked the song or not
    public boolean getSongIsLiked() {
        return songIsLiked;
    }

    public String toString() {
        String theSong = songTitle + ", " + songArtist + ", " + songGenre;
        return theSong;
    }

    public void setSongIsLikedToFalse(boolean songIsLiked) {
        this.songIsLiked = false;
        EventLog.getInstance().logEvent(new Event("Song is no longer liked."));
    }

    //EFFECTS: turns a song into a Json object
    //REQUIRES: song is not null
    //MODIFIES: this
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("songTitle", songTitle);
        json.put("songArtist", songArtist);
        json.put("songGenre", songGenre);
        json.put("songIsLiked", songIsLiked);
        return json;
    }
}
