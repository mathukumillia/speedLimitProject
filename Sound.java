import java.applet.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

/**
* Sound
*   object plays a specified sound file
*
**/
public class Sound {
	private AudioClip song; // Sound player

  private URL songPath; // Sound path

  /**
  * Constructor
  *     finds the given audio file and sets the song and songPath variables
  *
  * @param filename - the name of the audio file
  *
  **/
  public Sound(String filename)

  {

     try

     {

    songPath = (this.getClass().getResource(filename)); // Get the Sound URL

    song = Applet.newAudioClip(songPath); // Load the Sound

     }

     catch(Exception e){} // Satisfy the catch

  }

  /**
  * playSound
  *    plays the sound
  *
  **/
  public void playSound()

  {

     song.loop(); // Play

  }

  /**
  * stopSound
  *    stops playing the sound
  *
  **/
  public void stopSound()

  {

     song.stop(); // Stop

  }

}