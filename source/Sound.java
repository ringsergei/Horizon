import javax.sound.sampled.*;
import java.applet.*;
import java.net.URL;
import java.io.*;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
 

class Sound{
   private URL url;
   public FloatControl control;
   public Clip clip;
   boolean playCompleted;

   public Sound() {
   }

   public void playSound(String type, String name){
      try{
         if( type.equals("mainTheme") ) {
            url = this.getClass().getClassLoader().getResource("music//" + name + ".wav");
         }
         //URL url = this.getClass().getClassLoader().getResource("sounds//Hardware.wav");
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);

         // Get a sound clip resource.
         clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
         control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
         control.setValue(-10.0f);
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      } 
   }

}

  /* public void obsSound(String type){
      try {
         // Open an audio input stream.
         if( type=="wall" ){
           url = this.getClass().getClassLoader().getResource("sounds//obs2.wav");
         }
         else if( type=="villian" ){
            url = this.getClass().getClassLoader().getResource("sounds//obs.wav");
         }
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
         Clip clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.open(audioIn);
         clip.start();
         final FloatControl control = (FloatControl) 
                 clip.getControl(FloatControl.Type.MASTER_GAIN);
         control.setValue(0.0f);
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      } 
   }

   public void shotSound(){
        try {
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader().getResource("sounds//gunshot4.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
            final FloatControl control = (FloatControl) 
                    clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(0.0f);
         } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         } catch (LineUnavailableException e) {
            e.printStackTrace();
         } 
   }

   public void mainTheme(){
        try {
            // Open an audio input stream.
            URL url = this.getClass().getClassLoader().getResource("sounds//Hardware.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
            final FloatControl control = (FloatControl) 
                    clip.getControl(FloatControl.Type.MASTER_GAIN);
            control.setValue(-10.0f);
         } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         } catch (LineUnavailableException e) {
            e.printStackTrace();
         }
   }
}*/

