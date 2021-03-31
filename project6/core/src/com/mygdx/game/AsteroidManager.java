package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.ArrayList;
import java.util.List;

public class AsteroidManager {
    private List<Asteroid> available;
    private List<Asteroid> onScreen;
    private int height;
    private int width;
    private Stage stage;
    private int curr = 0;
    private Sprite[] sprites;

    public AsteroidManager(int h, int w, Stage s){
        available = new ArrayList<Asteroid>();
        onScreen = new ArrayList<Asteroid>();
        generateAsteroids();
        height = h;
        width = w;
        stage = s;
    }

    private void generateAsteroids(){
        for(int i = 1; i < 4; i++){
            for(int j = 0; j < 4; j++){
                available.add(new Asteroid(i, j * 90));
            }
        }
    }

    public void add(){
        if(available.size() == 0){
            generateAsteroids();
        }
        Asteroid a = available.remove((int)(Math.random() * available.size()));
        int h = height;
        int w = (int)(Math.random() * (width - a.getWidth()));
        a.setPosition(w, h);
        stage.addActor(a);
        onScreen.add(a);
    }

    public void tick(){
        for(int i = 0; i < onScreen.size(); i++){
            Asteroid a = onScreen.get(i);
            a.setPosition(a.getX(), a.getY() - a.tick());
            if(a.getY() <= (-1 * a.getHeight())){
                a.remove();
                onScreen.remove(i);
                available.add(a);
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
