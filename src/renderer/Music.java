package renderer;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * A class to handle running of music within the game.
 *
 * @author Philip Oliver - 300398228 *
 */
public class Music {
  private String currentFile;
  private static MediaPlayer player;
  private static final List<String> TRACK_NAMES = Arrays
      .asList(new String[] { "tunnels", "escape", "mysteries" });

  /**
   * Create a new Music object. Runs tunnels.wav as default.
   */
  public Music() {
    currentFile = "tunnels";
    playTrack();
  }

  /**
   * Update the media player to play the file specified by track. If the file is not a valid media
   * file or that track is already playing, don't do anything.
   *
   * @param track the name of the media file to play
   */
  public void update(String track) {
    if (TRACK_NAMES.contains(track) && !track.equals(currentFile)) {
      currentFile = track;
      player.stop();
      playTrack();
    }
  }

  private void playTrack() {
    Media track = new Media(new File("src" + File.separator + "renderer" + File.separator + "music"
        + File.separator + currentFile + ".wav").toURI().toString());
    player = new MediaPlayer(track);
    player.setOnEndOfMedia(new Runnable() {
      public void run() {
        player.seek(Duration.ZERO);
      }
    });
    player.play();
  }

  /**
   * Mute the music player.
   */
  public void mute() {
    player.setVolume(0);
  }

  public void unmute() {
    player.setVolume(1);
  }
}
