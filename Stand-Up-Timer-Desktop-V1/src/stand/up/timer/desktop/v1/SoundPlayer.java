package stand.up.timer.desktop.v1;

import java.io.BufferedInputStream;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class SoundPlayer {

    public static void playSound(String filename) {
        // Ensure filename is relative to the class location
        try (InputStream audioSrc = SoundPlayer.class.getResourceAsStream(filename)) {
            if (audioSrc == null) {
                System.out.println("Audio file not found: " + filename);
                return;
            }

            try (BufferedInputStream bufferedIn = new BufferedInputStream(audioSrc)) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                
                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                });
                
                clip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

