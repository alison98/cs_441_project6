package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public class LaserManager {
    private List<Laser> onScreen;
    private List<Laser> available;
    private int height;
    private int width;
    private int startHeight;
    private Stage stage;
    private final int v = 5;
    private int curr = 1;

    public LaserManager(int h, int w, int sh, Stage s){
        onScreen = new ArrayList<Laser>();
        available = new ArrayList<Laser>();
        height = h;
        width = w;
        startHeight = sh - 80;
        stage = s;
    }

    public List<Laser> getOnScreen(){
        return onScreen;
    }

    public void remove(int index){
        Laser l = onScreen.remove(index);
        l.remove();
        available.add(l);
    }

    private void add(int pos){
        Laser l;
        if(available.size() > 0) {
            l = available.remove(0);
        } else{
            l = new Laser();
        }
        l.setPosition(pos, startHeight);
        stage.addActor(l);
        onScreen.add(l);
    }

    public void tick(int rocketPos){
        for(int i = 0; i < onScreen.size(); i++){
            Laser l = onScreen.get(i);
            l.setPosition(l.getX(), l.getY() + v);
            if(l.getY() >= height){
                l.remove();
                onScreen.remove(i);
                available.add(l);
                i--;
            }
        }
        curr--;
        if(curr <= 0){
            add(rocketPos);
            curr = 50;
        }
    }
}
