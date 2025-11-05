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
        this.studyTime = Duration.ofSeconds(25); //10
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
        this.studyTime = stringtoDuration(studyTime);
        this.breakTime = stringtoDuration(breakTime);
        isLoop = false;
        volume = 50;
    }

    public Duration stringtoDuration(String time) {
            try {
                time = time.trim();
                if (time.startsWith("P") || time.startsWith("p")) {
                    // ISO-8601 duration notation starts with 'P'
                    return Duration.parse(time);
                } else if (time.matches("\\d{1,2}:\\d{2}:\\d{2}")) {
                    // Matches hh:mm:ss format
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
    
    public String durationToString(Duration d) {
        String time = "00:00:00";
        try {
            if(d == null) return time;
            
            long hours = d.toHours();
            long minutes = d.toMinutes() % 60;
            long seconds = d.getSeconds() % 60;

            time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } catch (Exception e) {
            System.out.println("Error incorrect format: " + e.getMessage());
        }
        return time;
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
    
    public void setSittingTime(String time) {
        Duration d = stringtoDuration(time);
        this.studyTime = d;
    }
    
    public void setStandingTime(String time) {
        Duration d = stringtoDuration(time);
        this.breakTime = d;
    }
    
    public String getSittingTime() {
        String time = durationToString(this.studyTime);
        return time;
    }
    
    public String getStandingTime() {
        String time = durationToString(this.breakTime);
        return time;
    }
    
    public String[] getTimeLabel() {
        return studyBreak;
    }
    
    public String getSfx() {
        return sfx;
    }
}

