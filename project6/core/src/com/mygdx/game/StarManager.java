package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public class StarManager {
    private List<Star> available;
    private List<Star> onScreen;
    private int height;
    private int width;
    private Stage stage;
    private int curr = 0;
    private Sprite[] sprites;

    public StarManager(int h, int w, Stage s){
        available = new ArrayList<Star>();
        onScreen = new ArrayList<Star>();
        height = h;
        width = w;
        stage = s;
        generateStars();
        initScreen();
    }

    private void initScreen(){
        for(int i = 0; i < 40; i++){
            add(true);
        }
    }

    private void generateStars(){
        for(int i = 1; i < 5; i++){
            available.add(new Star(i));
        }
    }

    public void add(boolean randomizeHeight){
        if(available.size() == 0){
            generateStars();
        }
        Star s = available.remove((int)(Math.random() * available.size()));
        int h;
        if(randomizeHeight)
            h = (int)(Math.random() * height);
        else
            h = height;
        int w = (int)(Math.random() * (width - s.getWidth()));
        s.setPosition(w, h);
        stage.addActor(s);
        onScreen.add(s);
    }

    public void tick(){
        for(int i = 0; i < onScreen.size(); i++){
            Star s = onScreen.get(i);
            s.setPosition(s.getX(), s.getY() - s.tick());
            if(s.getY() <= -50){
                s.remove();
                onScreen.remove(i);
                available.add(s);
                i--;
            }
        }
        if((int)(Math.random() * 25) == 7){
            add(false);
        }
    }
}
