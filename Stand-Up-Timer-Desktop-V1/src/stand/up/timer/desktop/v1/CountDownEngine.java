/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stand.up.timer.desktop.v1;
import java.time.Duration;

public class CountDownEngine {
    private final TimerDisplay timerDisplay;
    //private SoundPlayer SoundPlayer;
    //private ScheduledExecutorService executor;
    
    public CountDownEngine(TimerDisplay display) {
        this.timerDisplay = display;
    }
    
    //run the timer
    //play the music
    //end thread
    public void begin(Duration d, String label, int volume, String sfx) {
        try {
            long seconds = d.getSeconds();
            timerDisplay.updateDisplayLabel(label);
            for (long i = seconds; i >= 0; i--) {
                // Update display (UI)
                timerDisplay.updateDisplayVal(formatTime(i));
                Thread.sleep(1000); // wait 1 second
            }
            // Play sfx after countdown
            if (sfx != null) {
                SoundPlayer.playSound(sfx);
            }
        } catch (Exception e) {
            System.out.println("Countdown interrupted");
        }
    }
    
    private String formatTime(long totalSeconds) {
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    
}
