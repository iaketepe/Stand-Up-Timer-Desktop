/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stand.up.timer.desktop.v1;

public interface TimerDisplay {
    void updateDisplayVal(String uiValue); //hh:MM:SS
    void updateDisplayLabel(String uiLabel); //STUDY | BREAK
}
