package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Rocket extends Actor {
    private Sprite sprite;
    private int frames;
    private int curr;

    public Rocket(){
        sprite = new Sprite(new Texture(Gdx.files.internal("rocket-1-resized.png")));
        this.setBounds(this.sprite.getX(), this.sprite.getY(), this.sprite.getWidth(), this.sprite.getHeight());
        frames = (int)(Math.random() * 5 + 2);
        curr = frames;
    }

    public void tick(){
        frames--;
        if(frames == 0){
            int num = (int)(Math.random() * 3 + 1);
            while(num == curr){
                num = (int)(Math.random() * 3 + 1);
            }
            String filename = "rocket-" + num + "-resized.png";
            sprite = new Sprite(new Texture(Gdx.files.internal(filename)));
            frames = (int)(Math.random() * 5 + 2);
            curr = frames;
        }
        positionChanged();
    }

    @Override
    public void draw(Batch batch, float alpha){
        sprite.draw(batch);
    }

    @Override
    public void positionChanged(){
        sprite.setPosition(getX(), getY());
    }

    @Override
    public boolean remove(){
        return super.remove();
    }
}
