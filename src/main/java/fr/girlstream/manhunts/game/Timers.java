package fr.girlstream.manhunts.game;


public class Timers {

    int timer = 0;

    public void add1ToTimer(){
        timer++;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public Object getFormatTimer(){
        return String.format("%d:%02d:%02d", timer / 3600, (timer % 3600) / 60, (timer % 60));
    }
}
