package ui;

import model.Event;
import model.EventLog;
import model.LikedSongs;
import model.Playlist;
import model.Song;
import persistence.JsonReader;
import persistence.JsonWriter;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GUI {
    private JFrame frame;
    private JPanel panel;
    //    private JPanel panel2;
    private JList<String> likedSongsList;
    private JList<String> playlistList;
    private LikedSongs likedSongs;
    private Playlist playlist;
    private Song song;
    private static final String JSON_PLAYLIST_STORE = "./data/playlist.json";
    private static final String JSON_LIKED_SONGS_STORE = "./data/likedsongs.json";
    private JsonWriter jsonPlaylistWriter;
    private JsonWriter jsonLikedSongsWriter;
    private JsonReader jsonPlaylistReader;
    private JsonReader jsonLikedSongsReader;
    DefaultListModel<String> model1 = new DefaultListModel<>();
    DefaultListModel<String> model2 = new DefaultListModel<>();

    //EFFECTS: makes the GUI
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public GUI() throws IOException {                                                      //constructor
        jsonPlaylistWriter = new JsonWriter(JSON_PLAYLIST_STORE);
        jsonLikedSongsWriter = new JsonWriter(JSON_LIKED_SONGS_STORE);
        jsonPlaylistReader = new JsonReader(JSON_PLAYLIST_STORE);
        jsonLikedSongsReader = new JsonReader(JSON_LIKED_SONGS_STORE);
        playlist = new Playlist(null, null, 0);
        likedSongs = new LikedSongs("Liked Songs");
        song = new Song(null, null, null, false);
        likedSongsList = new JList<String>();
        playlistList = new JList<String>();
        likedSongsList.setModel(model1);
        playlistList.setModel(model2);

        frame = new JFrame();
        panel = new JPanel(new GridLayout(15, 4));
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 400);
        frame.setTitle("Spotify Organizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel, BorderLayout.CENTER);

        createJButtons();
        panel.setVisible(true);
        JOptionPane.showMessageDialog(frame, "Welcome to Spotify Organizer!");
        BufferedImage spotifyLogo = ImageIO.read(new File("./data/SpotifyLogo.png"));
        JLabel picLabel = new JLabel(new ImageIcon(spotifyLogo));
        picLabel.setVisible(true);
        picLabel.setOpaque(true);
        picLabel.setSize(40, 40);
        frame.add(picLabel, BorderLayout.EAST);
        panel.add(likedSongsList);
        panel.add(playlistList);
    }

    //MODIFIES: this
    //EFFECTS: creates JButtons for GUI
    private void createJButtons() {
        initLoadPlaylistButton();
        initLoadLikedSongsButton();
        initAddToLikedButton();
        initRemoveFromLikedButton();
        initCreateNewPlaylistButton();
        initAddToPlaylistButton();
        initSaveLikedButton();
        initSavePlaylistButton();
        initQuitOrganizerButton();
        panel.repaint();
    }

    //MODIFIES: panel
    //EFFECTS: makes the load playlist button
    private void initLoadPlaylistButton() {
        JButton loadPlaylistFromFileButton = new JButton("Load Playlist From File");
        loadPlaylistFromFileButton.setBounds(10, 170, 200, 20);
        panel.add(loadPlaylistFromFileButton);
        loadPlaylistFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPlaylistFromFileButtonActionPerformed();
            }
        });
    }

    //EFFECTS: makes the load liked songs button
    //MODIFIES: panel
    private void initLoadLikedSongsButton() {
        JButton loadLikedSongsFromFileButton = new JButton("Load Liked Songs From File");
        loadLikedSongsFromFileButton.setBounds(10, 150, 200, 20);
        panel.add(loadLikedSongsFromFileButton);
        loadLikedSongsFromFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadLikedSongsFromFileButtonActionPerformed();
            }
        });
    }

    //EFFECTS: makes the add to liked songs button
    //MODIFIES: panel
    private void initAddToLikedButton() {
        JButton addToLikedSongsButton = new JButton("Add to Liked Songs");
        addToLikedSongsButton.setBounds(10, 50, 200, 20);
        panel.add(addToLikedSongsButton);
        addToLikedSongsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToLikedSongsButtonActionPerformed();
            }
        });
    }

    //MODIFIES: panel
    //EFFECTS: makes the remove from liked songs button
    private void initRemoveFromLikedButton() {
        JButton removeFromLikedSongsButton = new JButton("Remove from Liked Songs");
        removeFromLikedSongsButton.setBounds(10, 70, 200, 20);
        panel.add(removeFromLikedSongsButton);
        removeFromLikedSongsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFromLikedSongsButtonActionPerformed();
            }
        });
    }

    //MODIFIES: panel
    //EFFECTS: makes the create new playlist button
    private void initCreateNewPlaylistButton() {
        JButton createNewPlaylistButton = new JButton("Create New Playlist");
        createNewPlaylistButton.setBounds(10, 90, 200, 20);
        createNewPlaylistButton.setVisible(true);
        panel.add(createNewPlaylistButton);
        createNewPlaylistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewPlaylistButtonActionPerformed();
            }
        });
    }

    //EFFECTS: makes the add to playlist button
    //MODIFIES: panel
    private void initAddToPlaylistButton() {
        JButton addToPlaylistButton = new JButton("Add to Playlist");
        addToPlaylistButton.setBounds(10, 30, 200, 20);
        panel.add(addToPlaylistButton);
        addToPlaylistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToPlaylistButtonActionPerformed();
            }
        });
    }

    //EFFECTS: makes the save liked songs button
    //MODIFIES: panel
    private void initSaveLikedButton() {
        JButton saveLikedSongsButton = new JButton("Save Liked Songs");
        saveLikedSongsButton.setBounds(10, 110, 200, 20);
        panel.add(saveLikedSongsButton);
        saveLikedSongsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveLikedSongsButtonActionPerformed();
            }
        });
    }

    //MODIFIES: panel
    //EFFECTS: makes the save playlist button
    private void initSavePlaylistButton() {
        JButton savePlaylistButton = new JButton("Save Playlist");
        savePlaylistButton.setBounds(10, 130, 200, 20);
        panel.add(savePlaylistButton);
        savePlaylistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePlaylistButtonActionPerformed();
            }
        });
    }


    //MODIFIES: panel
    //EFFECTS: make the quit organizer button
    private void initQuitOrganizerButton() {
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(500, 500, 100, 20);
        panel.add(quitButton);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitButtonActionPerformed();
            }
        });
    }

    //EFFECTS: quits the organizer and prints all events done to log
    private void quitButtonActionPerformed() {
        JOptionPane.showMessageDialog(frame, "Goodbye!");
        frame.setVisible(false);
        frame.dispose();
        printLog(EventLog.getInstance());
    }

    //EFFECTS: does the save playlist functionality
    private void savePlaylistButtonActionPerformed() {
        try {
            jsonPlaylistWriter.open();
            jsonPlaylistWriter.writePlaylist(playlist);
            jsonPlaylistWriter.close();
            JOptionPane.showMessageDialog(frame, "Your playlist " + playlist.getPlaylistName() + " has been saved");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Cannot write to file:" + JSON_PLAYLIST_STORE);
        }
    }

    //EFFECTS: does the save liked sings functionality
    private void saveLikedSongsButtonActionPerformed() {
        try {
            jsonLikedSongsWriter.open();
            jsonLikedSongsWriter.writeLikedSongs(likedSongs);
            jsonLikedSongsWriter.close();
            JOptionPane.showMessageDialog(frame, "Your " + likedSongs.getName() + " have been saved");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "Cannot write " + likedSongs.getName() + " to file");
        }
    }

    //EFFECTS: does the add to playlist functionality
    private void addToPlaylistButtonActionPerformed() {
        String songTitle = JOptionPane.showInputDialog(frame, "Song title to add?");
        for (Song s : (likedSongs.getLikedSongs())) {
            if (songTitle.equals(s.getSongTitle())) {
                playlist.addToPlaylist(s);
            }
        }
        String playlistName = JOptionPane.showInputDialog(frame, "Playlist name to add to?");
        for (Song s : playlist.getPlaylist()) {
            if (s.getSongIsLiked() == true && (s.getSongGenre().equals(playlist.getPlaylistGenre()))) {
                playlist.addToPlaylist(song);
                JOptionPane.showMessageDialog(frame, "Song has been added to playlist");
                model2.addElement(s.toString());
                panel.repaint();
            } else {
                JOptionPane.showMessageDialog(frame, "Song genre does not match playlist genre or is not liked");
            }
        }
        panel.setVisible(true);
        panel.repaint();
    }

    //EFFECTS: does the create new playlist functionality
    private void createNewPlaylistButtonActionPerformed() {
        String playlistName = JOptionPane.showInputDialog(frame, "Name of playlist?");
        String playlistGenre = JOptionPane.showInputDialog(frame, "Genre?");
        int playlistLength = Integer.valueOf(JOptionPane.showInputDialog(frame,
                "Length of Playlist? Type 0 for any new playlist"));
        playlist = new Playlist(playlistName, playlistGenre, playlistLength);
        JOptionPane.showMessageDialog(frame, "Playlist " + playlistName + " has been created");
        panel.repaint();
    }

    //EFFECTS: does the remove from liked songs functionality
    private void removeFromLikedSongsButtonActionPerformed() {
        String songTitle = JOptionPane.showInputDialog(panel, "Song title to remove?");
        for (Song s : likedSongs.getLikedSongs()) {
            if (s.getSongTitle().equals(songTitle)) {
                s.setSongIsLikedToFalse(s.getSongIsLiked());
                likedSongs.removeFromLikedSongs(s);
                model1.removeElement(s.toString());
            }
        }
        panel.repaint();
        JOptionPane.showMessageDialog(frame, songTitle + " has been removed from your liked songs");
    }

    //EFFECTS: does the add to liked songs functionality
    private void addToLikedSongsButtonActionPerformed() {
        String songTitle = JOptionPane.showInputDialog(frame, "Song title to add?");
        String songArtist = JOptionPane.showInputDialog(frame, "Artist Name?");
        String songGenre = JOptionPane.showInputDialog(frame, "Genre of song?");
        boolean songIsLiked = Boolean.parseBoolean(JOptionPane.showInputDialog(frame,
                "Do you like song? " + "Type true or false"));
        song = new Song(songTitle, songArtist, songGenre, songIsLiked);

        if (songIsLiked == true) {
            likedSongs.addToLikedSongs(song);
            JOptionPane.showMessageDialog(frame, "Song has been added to your liked songs");
            model1.addElement(song.toString());
            panel.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Failed to add. " + "Song is not liked!");
        }
        //model.addElement(likedSongs);// iterate through likedSongs and add each song to the model using addElement
        // make a toString function in Song class.
        panel.setVisible(true);
    }

    //EFFECTS: does the load liked songs functionality
    private void loadLikedSongsFromFileButtonActionPerformed() {
        try {
            likedSongs = jsonLikedSongsReader.readLikedSongs();
            JOptionPane.showMessageDialog(frame, "Your " + likedSongs.getName() + " have been loaded");
            //WANT TO SHOW THE LIKED SONGS LIST THAT LOADED IN THE FRAME
            for (String s : likedSongs.toList()) {
                //model1.addElement(s.toString());
                model1.addElement(s);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Unable to read" + JSON_LIKED_SONGS_STORE + " from file");
        }
    }

    //EFFECTS: does the load playlist functionality
    private void loadPlaylistFromFileButtonActionPerformed() {
        try {
            String playlistToLoad = JOptionPane.showInputDialog(frame, "Name of playlist to load?");
            if (playlistToLoad == playlist.getPlaylistName()) {
                playlist = jsonPlaylistReader.readPlaylist();
                JOptionPane.showMessageDialog(frame, "Playlist " + playlist.getPlaylistName()
                        + " has been loaded");

                //NEEDS A FOR LOOP
                model2.addElement(playlistToLoad.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Unable to read " + JSON_PLAYLIST_STORE + " from file");
        }
    }

    //EFFECTS: prints the log for every event that occurs
    //MODIFIES: console
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }
}

