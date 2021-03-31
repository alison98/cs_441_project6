package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public class LaserManager {
    private List<Laser> list;
    private List<Laser> offScreen;
    private int height;
    private int width;
    private int startHeight;
    private Stage stage;
    private final int v = 5;
    private int curr = 1;

    public LaserManager(int h, int w, int sh, Stage s){
        list = new ArrayList<Laser>();
        offScreen = new ArrayList<Laser>();
        height = h;
        width = w;
        startHeight = sh - 80;
        stage = s;
    }

    private void add(int pos){
        Laser l;
        if(offScreen.size() > 0) {
            l = offScreen.remove(0);
        } else{
            l = new Laser();
        }
        l.setPosition(pos, startHeight);
        stage.addActor(l);
        list.add(l);
    }

    public void tick(int rocketPos){
        for(int i = 0; i < list.size(); i++){
            Laser l = list.get(i);
            l.setPosition(l.getX(), l.getY() + v);
            if(l.getY() >= height){
                l.remove();
                list.remove(i);
                offScreen.add(l);
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
