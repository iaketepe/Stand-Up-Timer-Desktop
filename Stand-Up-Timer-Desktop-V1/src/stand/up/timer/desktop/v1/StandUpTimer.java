/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stand.up.timer.desktop.v1;
import java.time.Duration;

public class StandUpTimer{
    private TimerSettings timerSettings;
    private CountDownEngine countDownEngine;
    //private TimerDisplay timerDisplay;
    private Thread threadholder;
    
    public StandUpTimer(TimerDisplay display) {
        this.timerSettings = new TimerSettings();
        //this.timerDisplay = display;
        this.countDownEngine = new CountDownEngine(display);
    }
    
    public StandUpTimer(Duration DurationStudyTime, Duration DurationBreakTime) { // settings we want to import in
        this.timerSettings = new TimerSettings(DurationStudyTime, DurationBreakTime);
    }
    
    public StandUpTimer(String StudyTime, String BreakTime) { // settings we want to import in
        this.timerSettings = new TimerSettings(StudyTime, BreakTime);
    }
    
    
    public void startTimer() {
        threadholder = new Thread(() -> {
            try {
                do {
                    countDownEngine.begin(timerSettings.studyTime,timerSettings.getVolume(),timerSettings.getSfx());
                    countDownEngine.begin(timerSettings.breakTime,timerSettings.getVolume(),timerSettings.getSfx()); //sfx, ui componets, timer label
                } while (timerSettings.isLoop());
            } catch (Exception e) {
                System.out.println("Thread was Canceled.");
            }
        });
    }
    
    public void stopTimer() {
        threadholder.interrupt();
    }
    
    public void loopTimer() {
        timerSettings.updateLoop();
    }
    
    public void changeVolume(int volume) {
        timerSettings.setVolume(volume);
    }
    
}
