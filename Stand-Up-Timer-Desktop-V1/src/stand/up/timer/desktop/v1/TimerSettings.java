/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stand.up.timer.desktop.v1;
import java.time.Duration;

public class TimerSettings {
    Duration studyTime;
    Duration breakTime;
    boolean isLoop;
    int volume;
    String[] studyBreak = {"Study Time","Break Time"};
    String sfx = "alarmsfx.wav";
    
    public TimerSettings()  {
        this.studyTime = Duration.ofMinutes(25); //10
        this.breakTime = Duration.ofSeconds(25);
        isLoop = false;
        volume = 50;
    }
    
    public TimerSettings(Duration studyTime, Duration breakTime)  {
        this.studyTime = studyTime;
        this.breakTime = breakTime;
        isLoop = false;
        volume = 50;
    }
    
    public TimerSettings(String studyTime, String breakTime) {
        this.studyTime = StringtoDuration(studyTime);
        this.breakTime = StringtoDuration(breakTime);
        isLoop = false;
        volume = 50;
    }

    public Duration StringtoDuration(String time) {
            try {
                if (time.startsWith("P") || time.startsWith("p")) {
                    // ISO-8601 duration notation starts with 'P'
                    return Duration.parse(time);
                } else if (time.matches("\\d{1,2}:\\d{2}:\\d{2}")) {
                    // Matches HH:mm:ss format
                    String[] parts = time.split(":");
                    int h = Integer.parseInt(parts[0]);
                    int m = Integer.parseInt(parts[1]);
                    int s = Integer.parseInt(parts[2]);
                    return Duration.ofHours(h).plusMinutes(m).plusSeconds(s);
                } else {
                    throw new IllegalArgumentException("Unsupported format");
                }
            } catch (Exception e) {
                System.out.println("ERROR Incorrect String Format: " + e.getMessage());
                return Duration.ZERO;  // or throw, or return null as per your design
            }
    }

    public Duration getStudy(){
        return studyTime;
    }
    
    public Duration getBreak(){
        return breakTime;
    }
    
    public boolean isLoop(){
        return isLoop;
    }
    
    public int getVolume(){
        return volume;
    }
    
    public void updateLoop() {
        isLoop = !isLoop;
        System.out.println(isLoop);
    }
    
    public void setVolume(int vol) {
        if(vol > 100) vol = 100;
        else if (vol < 0) vol = 0;
        else {
            volume = vol;
        }
    }
    
    public String[] getTimeLabel() {
        return studyBreak;
    }
    
    public String getSfx() {
        return sfx;
    }
}

