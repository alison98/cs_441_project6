package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public class AsteroidManager {
    private List<Asteroid> list;
    private int height;
    private int width;
    private Stage stage;
    private int curr = 0;

    public AsteroidManager(int h, int w, Stage s){
        list = new ArrayList<Asteroid>();
        height = h;
        width = w;
        stage = s;
    }

    public void add(){
        Asteroid a = new Asteroid();
        int h = height;
        int w = (int)(Math.random() * (width - a.getWidth()));
        a.setPosition(w, h);
        stage.addActor(a);
        list.add(a);
    }

    public void tick(){
        for(int i = 0; i < list.size(); i++){
            Asteroid a = list.get(i);
            a.setPosition(a.getX(), a.getY() - a.tick());
            if(a.getY() <= (-1 * a.getHeight())){
                a.remove();
                list.remove(i);
                i--;
            }
        }
        curr--;
        if(curr <= 0){
            add();
            curr = (int)(Math.random() * 10 + 200);
        }
    }
}
