package ui;

import model.LikedSongs;
import model.Playlist;
import model.Song;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Spotify Organizer Application
public class SpotifyOrganizerApp {
    private Playlist playlist;
    private Song song;
    private LikedSongs likedSongs;
    private Scanner input;
    private static final String JSON_PLAYLIST_STORE = "./data/playlist.json";
    private static final String JSON_LIKED_SONGS_STORE = "./data/likedsongs.json";
    private JsonWriter jsonPlaylistWriter;
    private JsonWriter jsonLikedSongsWriter;
    private JsonReader jsonPlaylistReader;
    private JsonReader jsonLikedSongsReader;

    //EFFECTS: runs the SpotifyOrganizerApp
    public SpotifyOrganizerApp() {
        runSpotifyOrganizer();
    }

    //MODIFIES: this
    //EFFECTS: processes the user input
    private void runSpotifyOrganizer() {
        boolean continueRunning = true;
        String command = null;

        init();

        while (continueRunning) {
            displayOptions();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit organizer")) {
                continueRunning = false;
            } else {
                processCommandGiven(command);
            }
        }
        System.out.println("\nSee ya!");
    }

    // MODIFIES: this
    // EFFECTS: processes the command given by the user
    private void processCommandGiven(String command) {
        if (command.equals("add to liked songs")) {
            doAddToLikedSongs();
        } else if (command.equals("remove from liked songs")) {
            doRemoveFromLikedSongs();
        } else if (command.equals("create new playlist")) {
            doMakeNewPlaylist();
        } else if (command.equals("add to playlist")) {
            doAddToPlaylist();
        } else if (command.equals("save playlist")) {
            doSavePlaylist();
        } else if (command.equals("save liked songs list")) {
            doSaveLikedSongsList();
        } else if (command.equals("load playlist from file")) {
            doLoadPlaylistFromFile();
        } else if (command.equals("load liked songs from file")) {
            doLoadLikedSongsFromFile();
        } else {
            System.out.println("Invalid command. Select another option");
        }
    }

    //MODIFIES: this
    //EFFECTS: creates new playlists, songs and initializes liked songs list
    private void init() {
        playlist = new Playlist("Pop Punk Vibe$", "Pop-Punk", 0);
        song = new Song("Good 4 U", "Olivia Rodrigo", "Pop-Punk", true);
        likedSongs = new LikedSongs("Liked Songs");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonPlaylistWriter = new JsonWriter(JSON_PLAYLIST_STORE);
        jsonLikedSongsWriter = new JsonWriter(JSON_LIKED_SONGS_STORE);
        jsonPlaylistReader = new JsonReader(JSON_PLAYLIST_STORE);
        jsonLikedSongsReader = new JsonReader(JSON_LIKED_SONGS_STORE);
    }

    private void displayOptions() {
        System.out.println("\nselect from:");
        System.out.println("\tadd to liked songs");
        System.out.println("\tremove from liked songs");
        System.out.println("\tcreate new playlist");
        System.out.println("\tadd to playlist");
        System.out.println("\tsave playlist");
        System.out.println("\tsave liked songs list");
        System.out.println("\tload playlist from file");
        System.out.println("\tload liked songs from file");
        System.out.println("\tquit organizer"); //quits the organizer
    }

    private void doAddToLikedSongs() {
        System.out.println("\nSong title to add?");
        String songTitle = input.next();
        System.out.println("\nArtist name?");
        String songArtist = input.next();
        System.out.println("\nGenre of song?");
        String songGenre = input.next();
        System.out.println("\nDo you like song?");
        Boolean songIsLiked = input.nextBoolean();
        song = new Song(songTitle, songArtist, songGenre, songIsLiked);

        if (songIsLiked == true) {
            likedSongs.addToLikedSongs(song);
            System.out.println("Song has been added to you liked songs");
        } else {
            System.out.println("Song is not liked!");
        }
        viewLikedSongs();
    }

    private void doRemoveFromLikedSongs() {
        System.out.println("\nSong title to remove?");
        String songTitle = input.next();
        /*System.out.println("\nArtist name?");
        String songArtist = input.next();
        System.out.println("\nGenre of song?");
        String songGenre = input.next();
        System.out.println("\nDo you like song?");
        Boolean songIsLiked = input.nextBoolean();
        song = new Song(songTitle, songArtist, songGenre, songIsLiked);*/
        Song temporarySong = null;
        for (Song s : likedSongs.getLikedSongs()) {
            if (s.getSongTitle().equals(songTitle)) {
                s.setSongIsLikedToFalse(s.getSongIsLiked());
                temporarySong = s;
            }
        }
        likedSongs.removeFromLikedSongs(temporarySong);
        System.out.println("Song has been removed from your liked songs");
        viewLikedSongs();
    }

    private void doAddToPlaylist() {
        System.out.println("\nSong title to add?");
        String songTitle = input.next();
        for (Song s : (likedSongs.getLikedSongs())) {
            if (songTitle.equals(s.getSongTitle())) {
                playlist.addToPlaylist(s);
            }
        }
        System.out.println("\nPlaylist name to add to?");
        String playlistName = input.next();
        for (Song s : playlist.getPlaylist()) {
            if (s.getSongIsLiked() == true && (s.getSongGenre().equals(playlist.getPlaylistGenre()))) {
                playlist.addToPlaylist(song);
                System.out.println("Song has been added to playlist");
            } else {
                System.out.println("Song genre does not match playlist genre or is not liked");
            }
        }
        viewPlaylist();
    }

    /*        System.out.println("\nSong title to add?");
    String songTitle = input.next();
        for (Song s : (likedSongs.getLikedSongs())) {
        if (songTitle.equals(s.getSongTitle())) {
            System.out.println("\nPlaylist name to add to?");
            String playlistName = input.next();
            for (Song song1 : playlist.getPlaylist()) {
                if (s.getSongIsLiked() == true && (s.getSongGenre().equals(playlist.getPlaylistGenre()))) {
                    playlist.addToPlaylist(song);
                    System.out.println("Song has been added to playlist");
                }
            }
        } else {
            System.out.println("Song genre does not match playlist genre or is not liked");
        }
        viewPlaylist();
    }
}*/

    /*System.out.println("\nArtist name?");
        String songArtist = input.next();
        System.out.println("\nGenre of song?");
        String songGenre = input.next();
        System.out.println("\nDo you like song?");
        Boolean songIsLiked = input.nextBoolean();*/

    /*System.out.println("\nGenre of playlist?");
        String playlistGenre = input.next();
        System.out.println("\nPlaylist length?");
        int playlistLength = input.nextInt();*/

    public void doMakeNewPlaylist() {
        System.out.println("Name?");
        String playlistName = input.next();
        System.out.println("Genre?");
        String playlistGenre = input.next();
        System.out.println("Length?");
        int playlistLength = input.nextInt();
        playlist = new Playlist(playlistName, playlistGenre, playlistLength);
        System.out.println("playlist has been created");
    }

    // EFFECTS: saves the playlist to file
    private void doSavePlaylist() {
        try {
            jsonPlaylistWriter.open();
            jsonPlaylistWriter.writePlaylist(playlist);
            jsonPlaylistWriter.close();
            System.out.println("Saved " + playlist.getPlaylistName() + " to " + JSON_PLAYLIST_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot write to file:" + JSON_PLAYLIST_STORE);
        }
    }

    // EFFECTS: saves the liked songs to file
    private void doSaveLikedSongsList() {
        try {
            jsonLikedSongsWriter.open();
            jsonLikedSongsWriter.writeLikedSongs(likedSongs);
            jsonLikedSongsWriter.close();
            System.out.println("Saved " + likedSongs.getName() + " to " + JSON_LIKED_SONGS_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot write to file:" + JSON_LIKED_SONGS_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads playlist from file
    private void doLoadPlaylistFromFile() {
        try {
            playlist = jsonPlaylistReader.readPlaylist();
            System.out.println("Loaded " + playlist.getPlaylistName() + " from " + JSON_PLAYLIST_STORE);
            System.out.println(JSON_PLAYLIST_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_PLAYLIST_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads liked songs list from file
    private void doLoadLikedSongsFromFile() {
        try {
            likedSongs = jsonLikedSongsReader.readLikedSongs();
            System.out.println("Loaded " + likedSongs.getName() + " from " + JSON_LIKED_SONGS_STORE);
            System.out.println(JSON_LIKED_SONGS_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_LIKED_SONGS_STORE);
        }
    }

    //MODIFIES: nothing
    //EFFECTS: allows user to view their liked songs
    public void viewLikedSongs() {
        for (Song s : likedSongs.getLikedSongs()) {
            System.out.println(s.toString());
        }
    }

    //MODIFIES: nothing
    //EFFECTS: allows user to view their playlist
    public void viewPlaylist() {
        for (Song s : playlist.getPlaylist()) {
            System.out.println(s.toString());
        }
    }

}