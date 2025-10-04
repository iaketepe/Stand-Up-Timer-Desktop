/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stand.up.timer.desktop.v1;
import java.time.Duration;

public class CountDownEngine {
    private Duration time;
    private TimerDisplay timerDisplay;
    //sfx
    //private ScheduledExecutorService executor;
    
    public CountDownEngine(TimerDisplay display) {
        this.timerDisplay = display;
    }
    
    //run the timer
    //play the music
    //end thread
    public void begin(Duration d, int v, String sfx) {
        new Thread(() -> {
            
        });
    }
    
}
