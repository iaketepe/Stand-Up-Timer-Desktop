package stand.up.timer.desktop.v1;

import java.io.BufferedInputStream;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class SoundPlayer {

    public static void playSound(String filename, int volume) {
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
                
                
                if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                    FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    float vol = volume / 100f;
                    float dB = (float) (Math.log10(Math.max(vol, 0.0001)) * 20);
                    volumeControl.setValue(dB);
                }
                
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

